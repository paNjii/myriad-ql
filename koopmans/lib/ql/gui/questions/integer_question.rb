module QL
  module GUI
    class IntegerQuestion < TextQuestion
      include AST

      def initialize(args)
        super
        @variable.value = 0
        @variable.type  = IntegerType

        # SliderWidget.new(question: self, minimum: 0, maximum: 10)
        SpinboxWidget.new(question: self)
      end
    end
  end
end