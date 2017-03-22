# TODO use wrapper pattern to apply styles
module QLS
  module GUI
    class StylesheetBuilder
      attr_reader :question_frame_styles

      def initialize(defaults)
        @question_frame_styles = {}
        @defaults = defaults
      end

      def visit_stylesheet(stylesheet, _)
        stylesheet.pages.map { |page| page.accept(self) }
      end

      def visit_page(page)
        default = @defaults[page.object_id]
        page.body.map { |element| element.accept(self, default) }
      end

      def visit_section(section, parent_default)
        default = @defaults[section.object_id] || parent_default
        section.body.map { |element| element.accept(self, default)}
      end

      def visit_question(question, parent_default)
        properties =  @defaults[question.object_id] || parent_default
        @question_frame_styles[question.variable.name] = properties if properties
      end

      def visit_default_properties(_, _) end
    end
  end
end