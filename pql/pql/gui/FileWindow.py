# -*- coding: utf-8 -*-
from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QFileDialog
from PyQt5.QtWidgets import QListWidget
from PyQt5.QtWidgets import QPushButton
from PyQt5.QtWidgets import QVBoxLayout
from PyQt5.QtWidgets import QWidget

from pql.gui.Editor import Editor
from pql.gui.Questionnaire import Questionnaire
from pql.identifierchecker.identifierchecker import IdentifierChecker
from pql.parser.parser import parse


class FileWindow(QWidget):
    def __init__(self, argv, parent=None):
        super(FileWindow, self).__init__(parent)

        self.resize(280, 144)
        self.setWindowTitle("Leuker kunnen we het niet maken")
        self.center()
        self.setFocusPolicy(Qt.StrongFocus)

        button_load_file = QPushButton("Load file", self)
        button_load_file.resize(button_load_file.sizeHint())
        button_load_file.clicked.connect(self.handle_button)

        self.list_errors = QListWidget()
        self.list_errors.clear()
        self.list_errors.setStyleSheet("QListWidget {color: red;}")

        main_layout = QVBoxLayout()
        main_layout.addWidget(button_load_file)
        main_layout.addWidget(self.list_errors)

        self.setLayout(main_layout)

        if len(argv) > 1:
            self.handle_file(argv[1])
        else:
            self.show()

    def handle_button(self):
        file_path, _ = QFileDialog.getOpenFileName(filter="*.ql")
        self.handle_file(file_path)

    def handle_file(self, file_path):
        self.list_errors.clear()
        open_file = self.open_file(file_path)

        if open_file is not None:
            file_dict = {"file_path": file_path, "file_body": open_file}
            ql_ast = self.parse_file(open_file)

            if self.list_errors:
                list_errors = [error.text() for error in self.list_errors.findItems("", Qt.MatchContains)]
                Editor(file_dict, list_errors, parent=self).show()

            if ql_ast is not None:
                ql_ids, ql_errors = self.check_ids(ql_ast)

                if ql_errors:
                    self.list_errors.addItems(ql_errors)
                    list_errors = [error.text() for error in self.list_errors.findItems("", Qt.MatchContains)]
                    Editor(file_dict, list_errors, parent=self).show()
                else:
                    self.close()
                    self.show_questionnaire(ql_ast)

    @staticmethod
    def show_questionnaire(ql_ast):
        gui = Questionnaire()
        gui.visit(ql_ast).show()

    def closeEvent(self, event):
        event.accept()

    def check_ids(self, ql_ast):
        try:
            return IdentifierChecker().visit(ql_ast)
        except Exception as e:
            self.list_errors.addItem("Checking:\n    {}".format(e))

    def open_file(self, file_path):
        try:
            with open(file_path, 'r') as open_file:
                return open_file.read()
        except FileNotFoundError as fnfe:
            # self.list_errors.addItem("Given file could not be found:\n    {}".format(fnfe))
            print("Given file could not be found:\n    {}".format(fnfe))
        except Exception as e:
            self.list_errors.addItem("Opening:\n    {}".format(e))

    def parse_file(self, ql_str):
        try:
            return parse(ql_str)
        except Exception as e:
            self.list_errors.addItem("Parsing:\n    {}".format(e))

    def center(self):
        frame_geometry = self.frameGeometry()
        desktop = QApplication.desktop()
        screen = desktop.screenNumber(desktop.cursor().pos())
        center_point = desktop.screenGeometry(screen).center()
        frame_geometry.moveCenter(center_point)
        self.move(frame_geometry.topLeft())
