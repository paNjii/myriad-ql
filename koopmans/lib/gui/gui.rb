require_relative '../ast/expression'
require_relative '../ast/literal'
require_relative '../ast/variable'
require_relative '../ast/type'

require_relative 'gui_question'
require_relative 'boolean_question'
require_relative 'string_question'
require_relative 'computed_question'
require_relative 'gui_question_visitor'

require 'tk'
require 'pp'

class GUI < GUIQuestionVisitor
  def initialize(ast)
    super

    create_submit_button
    Tk.mainloop
  end

  def value_changed(question)
    @questions.each_value(&:refresh)
  end

  def submit
    p @questions.each_value.select{ |q| !q.hidden}.map(&:to_json)
  end

  def create_submit_button
    button = TkButton.new.grid(row: @questions.size + 2)
    button.text = 'Submit'
    button.command = proc { submit }
  end
end

class TkVariable
  attr_accessor :type

  def eval
    if type == BooleanType
      bool
    elsif type == StringType
      string
    elsif type == IntegerType
      numeric
    else
      value
    end
  end
end