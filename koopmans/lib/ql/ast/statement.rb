module QL
  module AST
    class IfStatement
      attr_reader :expression, :block

      def initialize(expression, block)
        @expression = expression
        @block      = block
      end

      def accept(visitor)
        visitor.visit_if_statement(self)
      end

      def accept_two_vars(visitor, condition)
        visitor.visit_if_statement(self, condition)
      end
    end

    class Question
      attr_reader :label, :variable, :type
      attr_accessor :condition, :assignment

      def initialize(label, variable, type, expression=nil, condition=nil)
        @label      = label.to_s
        @variable   = variable
        @type       = type
        @assignment = expression
        @condition  = condition
      end

      def accept(visitor)
        visitor.visit_question(self)
      end

      # TODO is going to be removed
      def accept_two_vars(visitor, condition)
        visitor.visit_question(self, condition)
      end

      def render(gui)
        if @assignment
          QL::GUI::ComputedQuestion.new(gui: gui, question: self)
        else
          @type.gui_question.new(gui: gui, question: self)
        end
      end
    end
  end
end



