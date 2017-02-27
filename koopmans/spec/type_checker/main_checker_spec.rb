require 'rspec'
require 'pp'
require 'require_all'

require_all 'lib'

describe TypeChecker do
  # create question
  question_variable = Variable.new('hasSoldHouse')
  question = Question.new('Did you sell a house in 2010?', question_variable, BooleanType.new)

  # create undefined computed question
  undefined_variable = Variable.new('_')
  undefined_question_variable = Variable.new('hasBoughtHouse')
  undefined_question = Question.new('Did you sell a house in 2010?', undefined_question_variable, IntegerType.new, undefined_variable)

  # create computed question
  computed_question_variable = Variable.new('valueResidue')
  computed_question = Question.new('Value residue:', computed_question_variable, IntegerType.new, Add.new(undefined_question_variable, question_variable))

  # create cyclic dependency
  cyclic_question_variable = Variable.new('privateDebt')
  cyclic_question_variable2 = Variable.new('sellingPrice')
  cyclic_question = Question.new('Private debts for the sold house:', cyclic_question_variable, IntegerType.new, Subtract.new(cyclic_question_variable, undefined_question_variable))
  cyclic_question2 = Question.new('What was the selling price?', cyclic_question_variable2, IntegerType.new, Subtract.new(cyclic_question_variable2, undefined_question_variable))

  # create if statement with a non boolean condition
  if_statement = IfStatement.new(undefined_question_variable, [question])

  # create the ast with all the errors
  ast = Form.new('_', [question, question, undefined_question, if_statement, computed_question, cyclic_question, cyclic_question2])

  describe DuplicateLabelChecker do
    it 'detects error' do
      expect(ast.accept(DuplicateLabelChecker.new)).to match(
        ["[WARNING]: question with label 'Did you sell a house in 2010?' is defined multiple times"]
      )
    end
  end

  describe DuplicateVariableChecker do
    it 'detects error' do
      expect(ast.accept(DuplicateVariableChecker.new)).to match(
        ["[ERROR]: variable 'hasSoldHouse' is defined multiple times"]
      )
    end
  end

  describe UndefinedVariableChecker do
    it 'detects error' do
      expect(ast.accept(UndefinedVariableChecker.new)).to match(
          ["[ERROR]: variable '_' is undefined"]
      )
    end
  end

  describe OperandsTypeChecker do
    it 'detects error' do
      expect(ast.accept(OperandsTypeChecker.new)).to match(
       ["[ERROR]: BooleanType can not be used with Add",
        "[ERROR]: IntegerType can not be used with BooleanType"]
      )
    end
  end

  describe CyclicChecker do
    it 'detects error' do
      expect(ast.accept(CyclicChecker.new)).to match(
       ["[ERROR]: question with variable 'privateDebt' has a cyclic dependency",
        "[ERROR]: question with variable 'sellingPrice' has a cyclic dependency"]
      )
    end
  end

end
