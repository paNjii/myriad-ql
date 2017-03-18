﻿using Antlr4.Runtime.Misc;
using Questionnaires.QL.AST.Literals;
using Questionnaires.QL.AST.Operators;
using Questionnaires.Types;
using System;
using System.Collections.Generic;
using System.Diagnostics;

namespace Questionnaires.QL.AST
{
    public class ASTBuilder : QLBaseVisitor<INode>
    {
        private Compilation.Result Messages;
        private CSTBuilder CSTBuilder;

        public ASTBuilder(Compilation.Result result)
        {
            Messages = result;
            CSTBuilder = new CSTBuilder(Messages);
        }

        public AST.Form BuildForm(string input)
        {
            var CST = CSTBuilder.BuildForm(input);
            if (Messages.ContainsErrors())
                throw new Compilation.ParseException();

            return Visit(CST) as Form;
        }

        public AST.IExpression BuildExpression(string input)
        {
            var CST = CSTBuilder.BuildExpression(input);
            if (Messages.ContainsErrors())
                throw new Compilation.ParseException();

            return Visit(CST) as IExpression;
        }

        public AST.ComputedQuestion BuildComputedQuestion(string input)
        {
            var CST = CSTBuilder.BuildComputedQuestion(input);
            if (Messages.ContainsErrors())
                throw new Compilation.ParseException();

            return Visit(CST) as ComputedQuestion;
        }

        private List<IStatement> GetStatements(QLParser.StatementContext[] context)
        {
            var statements = new List<IStatement>();
            foreach (QLParser.StatementContext statement in context)
            {
                statements.Add((dynamic)statement.Accept(this));
            }

            return statements;
        }

        public override INode VisitForm([NotNull] QLParser.FormContext context)
        {
            var identifier = context.Identifier().GetText();
            var statements = GetStatements(context.statement());
            var form = new Form(identifier, statements);

            return form;
        }

        public override INode VisitQuestion([NotNull] QLParser.QuestionContext context)
        {
            var identifier = context.Identifier().GetText();
            var body = context.StringLiteral().GetText();
            var type = context.Type().GetText();
            IType parsedType;

            switch (type)
            {
                case "boolean":
                    parsedType = new BooleanType();
                    break;
                case "money":
                    parsedType = new MoneyType();
                    break;
                case "int":
                    parsedType = new IntegerType();
                    break;
                case "string":
                    parsedType = new StringType();
                    break;
                default:
                    throw new ArgumentException();
            }

            return new Question(identifier, body, parsedType);
        }

        public override INode VisitConditionalBlock([NotNull] QLParser.ConditionalBlockContext context)
        {
            var expression = context.condition.Accept(this);
            Debug.Assert(expression is IExpression);

            var thenStatements = GetStatements(context.thenBlock.statement());
            var elseStatements = new List<IStatement>();
            if (context.elseBlock != null)
                elseStatements = GetStatements(context.elseBlock.statement());            

            return new Conditional((dynamic)expression, thenStatements, elseStatements);
        }

        public override INode VisitComputedQuestion([NotNull] QLParser.ComputedQuestionContext context)
        {
            var question = context.question().Accept(this);
            var expression = context.expression().Accept(this);
            return new ComputedQuestion((dynamic)question, (dynamic)expression);
        }

        public override INode VisitID([NotNull] QLParser.IDContext context)
        {
            return new Identifier(context.Identifier().GetText());
        }

        public override INode VisitBinaryOp([NotNull] QLParser.BinaryOpContext context)
        {
            var lhs = context.left.Accept(this);
            var rhs = context.right.Accept(this);

            switch (context.op.Text)
            {
                case "+":
                    return new Addition((dynamic)lhs, (dynamic)rhs);
                case "-":
                    return new Subtraction((dynamic)lhs, (dynamic)rhs);
                case "*":
                    return new Multiply((dynamic)lhs, (dynamic)rhs);
                case "/":
                    return new Division((dynamic)lhs, (dynamic)rhs);
                case ">":
                    return new GreaterThan((dynamic)lhs, (dynamic)rhs);
                case ">=":
                    return new GreaterThanOrEqual((dynamic)lhs, (dynamic)rhs);
                case "<":
                    return new LessThan((dynamic)lhs, (dynamic)rhs);
                case "<=":
                    return new LessThanOrEqual((dynamic)lhs, (dynamic)rhs);
                case "==":
                    return new Equal((dynamic)lhs, (dynamic)rhs);
                case "!=":
                    return new Inequal((dynamic)lhs, (dynamic)rhs);
                case "||":
                    return new Or((dynamic)lhs, (dynamic)rhs);
                case "&&":
                    return new And((dynamic)lhs, (dynamic)rhs);
                default:
                    throw new ArgumentException();
            }
        }

        public override INode VisitUnaryOp([NotNull] QLParser.UnaryOpContext context)
        {
            var operand = context.expression().Accept(this);

            switch (context.op.Text)
            {
                case "!":
                    return new Bang((dynamic)operand);
                case "+":
                    return VisitPositiveOperation((dynamic)operand);
                case "-":
                    return VisitNegativeOperation((dynamic)operand);
                default:
                    throw new ArgumentException();
            }
        }

        public INode VisitPositiveOperation(Number number)
        {
            return new Number("+" + number.ValueAsString);
        }

        public INode VisitPositiveOperation(Money money)
        {
            return new Money("+" + money.ValueAsString);
        }

        public INode VisitPositiveOperation(IExpression expression)
        {
            return new Positive(expression);
        }

        public INode VisitNegativeOperation(Number number)
        {
            return new Number("-" + number.ValueAsString);
        }

        public INode VisitNegativeOperation(Money money)
        {
            return new Money("-" + money.ValueAsString);
        }

        public INode VisitNegativeOperation(IExpression expression)
        {
            return new Negative(expression);
        }

        public override INode VisitMoney([NotNull] QLParser.MoneyContext context)
        {
            return new Money(context.GetText());
        }

        public override INode VisitBool([NotNull] QLParser.BoolContext context)
        {
            Debug.Assert(context.GetText() == "true" || context.GetText() == "false");
            return new Literals.Boolean(context.GetText() == "true");
        }

        public override INode VisitString([NotNull] QLParser.StringContext context)
        {
            return new Literals.String(context.GetText());
        }

        public override INode VisitNumber([NotNull] QLParser.NumberContext context)
        {
            return new Number(context.GetText());
        }

        public override INode VisitParens([NotNull] QLParser.ParensContext context)
        {
            return context.expression().Accept(this);
        }
    }
}