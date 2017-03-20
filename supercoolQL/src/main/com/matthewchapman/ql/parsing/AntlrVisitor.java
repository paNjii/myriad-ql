package com.matthewchapman.ql.parsing;

import com.matthewchapman.antlr.QLBaseVisitor;
import com.matthewchapman.antlr.QLParser;
import com.matthewchapman.ql.ast.*;
import com.matthewchapman.ql.ast.atomic.BooleanLiteral;
import com.matthewchapman.ql.ast.atomic.IntegerLiteral;
import com.matthewchapman.ql.ast.atomic.StringLiteral;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.ast.statement.IfElseStatement;
import com.matthewchapman.ql.ast.statement.IfStatement;
import com.matthewchapman.ql.ast.statement.Question;

import java.util.ArrayList;

/**
 * Created by matt on 24/02/2017.
 */
public class AntlrVisitor extends QLBaseVisitor<TreeNode> {

    @Override
    public TreeNode visitFormDeclaration(QLParser.FormDeclarationContext ctx) {
        String ID = ctx.ID().getText();
        ArrayList<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }

        return new Form(ID, statements);
    }

    @Override
    public TreeNode visitQuestion(QLParser.QuestionContext ctx) {
        String questionID = ctx.ID().getText();
        String questionContent = ctx.STRING().getText();

        Type questionReturnType = (Type) visit(ctx.type());
        ParameterGroup parameterGroup;

        if (ctx.calculatedValue() != null) {
            parameterGroup = (ParameterGroup) visit(ctx.calculatedValue());
        } else {
            parameterGroup = null;
        }

        return new Question(questionID, questionContent, questionReturnType, parameterGroup);
    }

    @Override
    public TreeNode visitIfStatement(QLParser.IfStatementContext ctx) {
        ArrayList<Statement> statements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }

        return new IfStatement((Expression) visit(ctx.expression()), statements);
    }

    @Override
    public TreeNode visitIfElseStatement(QLParser.IfElseStatementContext ctx) {

        ArrayList<Statement> ifCaseStatements = new ArrayList<>();
        ArrayList<Statement> elseCaseStatements = new ArrayList<>();

        for (QLParser.StatementContext statementContext : ctx.ifCase) {
            ifCaseStatements.add((Statement) visit(statementContext));
        }

        for (QLParser.StatementContext statementContext : ctx.elseCase) {
            elseCaseStatements.add((Statement) visit(statementContext));
        }

        return new IfElseStatement((Expression) visit(ctx.expression()), ifCaseStatements, elseCaseStatements);

    }

    @Override
    public TreeNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return new StringLiteral(ctx.STRING().getText());
    }

    @Override
    public TreeNode visitParameter(QLParser.ParameterContext ctx) {
        return new Parameter(ctx.ID().getText());
    }

    @Override
    public TreeNode visitLogicalAnd(QLParser.LogicalAndContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new LogicalAnd(left, right);
    }

    @Override
    public TreeNode visitLogicalOr(QLParser.LogicalOrContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new LogicalOr(left, right);
    }

    @Override
    public TreeNode visitNegation(QLParser.NegationContext ctx) {
        return new Negation((Expression) visit(ctx.expression()));
    }

    @Override
    public TreeNode visitSubtraction(QLParser.SubtractionContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new Subtraction(left, right);
    }

    @Override
    public TreeNode visitNotEqual(QLParser.NotEqualContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new NotEqual(left, right);
    }

    @Override
    public TreeNode visitParameterGroup(QLParser.ParameterGroupContext ctx) {
        ParameterGroup parameterGroup = new ParameterGroup();

        for (QLParser.ExpressionContext expressionContext : ctx.expression()) {
            parameterGroup.addExpression((Expression) visit(expressionContext));
        }

        return parameterGroup;
    }

    @Override
    public TreeNode visitDivision(QLParser.DivisionContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new Division(left, right);
    }

    @Override
    public TreeNode visitEqual(QLParser.EqualContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new Equal(left, right);
    }

    @Override
    public TreeNode visitLessThan(QLParser.LessThanContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new LessThan(left, right);
    }

    @Override
    public TreeNode visitGreaterThanEqualTo(QLParser.GreaterThanEqualToContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new GreaterThanEqualTo(left, right);
    }

    @Override
    public TreeNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        return new IntegerLiteral(ctx.NUMBER().getText());
    }

    @Override
    public TreeNode visitMultiplication(QLParser.MultiplicationContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new Multiplication(left, right);
    }

    @Override
    public TreeNode visitAddition(QLParser.AdditionContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new Addition(left, right);
    }

    @Override
    public TreeNode visitGreaterThan(QLParser.GreaterThanContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new GreaterThan(left, right);
    }

    @Override
    public TreeNode visitLessThanEqualTo(QLParser.LessThanEqualToContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        return new LessThanEqualTo(left, right);
    }

    @Override
    public TreeNode visitCalculatedValue(QLParser.CalculatedValueContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public TreeNode visitBooleanType(QLParser.BooleanTypeContext ctx) {
        return new BooleanLiteral(ctx.getText());
    }

    @Override
    public TreeNode visitIntegerType(QLParser.IntegerTypeContext ctx) {
        return new IntegerLiteral(ctx.getText());
    }

    @Override
    public TreeNode visitStringType(QLParser.StringTypeContext ctx) {
        return new StringLiteral(ctx.getText());
    }
}