# coding=utf-8
from tests.shared import Shared


class TestBooleanTypeChecker(Shared):
    def test_boolean_negation_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = !true
        }
        """
        environment = self.apply_evaluate(input_string)         
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_and_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true && true
        }
        """
        environment = self.apply_evaluate(input_string)         
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_or_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true || true
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_equality_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true == 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_equality_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true == true
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_equality_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true == 'abc'
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_equality_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true == 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_inequality_boolean_and_integer(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true != 5
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_inequality_boolean_and_boolean(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true != true
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_inequality_boolean_and_string(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true != 'abc'
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))

    def test_boolean_inequality_boolean_and_money(self):
        input_string = """
        form taxOfficeExample {
            "Value residue:" valueResidue: boolean = true != 2.00
        }
        """
        environment = self.apply_evaluate(input_string)
        expected_identifier = 'valueResidue'
        self.assertEqual(len(type_checker_result), 0, "This cases is not supposed to fail: following errors {}"
                         .format(type_checker_result))
