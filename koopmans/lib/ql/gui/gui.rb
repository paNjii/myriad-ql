require 'tk'

module QL
  module GUI
    class GUI
      def initialize(ql_ast, qls_ast, type_checker)
        @question_frames = ql_ast.accept(FormBuilder.new)
        @question_frames.each do |question_frame|
          question_frame.render
          question_frame.listen do
            reload_questions
          end
        end

        # StylesheetBuilder.new(qls_ast, ql_ast, self)

        reload_questions

        # row_position = @question_frames.size
        submit_button = SubmitButton.new
        submit_button.listen do
          print_form
        end

        Tk.mainloop
      end

      def reload_questions
        rendered_questions.each(&:reload)
      end

      def print_form
        pp enabled_questions.map(&:to_json)
      end

      def enabled_questions
        rendered_questions.each.select { |question| question.enabled }
      end

      def rendered_questions
        @question_frames.each.select { |question| question.tk_frame }
      end
    end
  end
end

# return if check(type_checker) == 'quit'

# TODO hier wat aan doen
# def check(type_checker)
#   if !type_checker[:errors].empty?
#     Tk.messageBox(
#       type:    'ok',
#       icon:    'error',
#       title:   'Errors found!',
#       message: type_checker[:errors].map(&:message).join('\n')
#     )
#     return 'quit'
#   elsif !type_checker[:warnings].empty?
#     Tk.messageBox(
#       type:    'ok',
#       icon:    'warning',
#       title:   'Warnings found!',
#       message: type_checker[:warnings].map(&:message).join('\n')
#     )
#     return 'continue'
#   end
# end