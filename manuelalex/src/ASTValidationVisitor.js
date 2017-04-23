/**
 * Created by alexvanmanen on 24-02-17.
 */

import find from 'lodash/find';
import {Expression} from './expressions/Expression.js';
import {MemoryState} from './memory/MemoryState.js';
import {QLMoney, QLNumber, QLDate, QLBoolean, QLString} from './types/Types.js';

export class ASTValidationVisitor {

    _reservedBooleanNames = ['true', 'false'];

    constructor() {
        this.memoryState = new MemoryState();
        this.errors = [];
        this.warnings = [];
        this.labels = [];
    }

    getMemoryState() {
        return this.memoryState;
    }

    visitAST(ast) {
        this.visitForm(ast.getProgram()); // todo maybe remove this
        this.visitStatements(ast.getStatements());
    }


    visitForm(form) {
        /* TODO, and fix visitor pattern */
    }

    visitStatements(statements) {
        for (const statement of statements) {
            statement.accept(this);
        }
    }

    visitQuestion(question) {

        let property = question.getProperty();
        property.accept(this);

        this.checkDuplicateDeclarations(question);
        this.checkDuplicateLabels(question);

    }

    visitAnswer(answer) {
        let allocation = answer.getAllocation();
        this.checkDuplicateLabels(answer);
        return allocation.accept(this);
    }

    visitAllocation(allocation) {
        let expression = allocation.getExpression();
        let returnType = expression.accept(this);
        if (returnType !== allocation.getType()) {
            this.errors.push(`Invalid alloction. The return type should be the same as the type proviced, at location ${allocation.getLocation()}. `);
        }
    }

    visitIfStatement(ifStatement) {
        let condition = ifStatement.getCondition();
        condition.accept(this);

        let ifBody = ifStatement.getIfBody();
        for (let statement of ifBody) {
            statement.accept(this);
        }
    }

    visitIfElseStatement(ifElseStatement) {
        let elseBody = ifElseStatement.getElseBody();
        for (let statement of elseBody) {
            statement.accept(this);
        }
        return this.visitIfStatement(ifElseStatement);
    }


    visitPrefixExpression(prefixExpression) {
        let expression = prefixExpression.getExpression();
        if (!(expression.accept(this) instanceof QLBoolean)) {
            this.errors.push(`Invalid expression. The prefix operator ${prefixExpression.getPrefix()} can not be applied to ${expression.getName}, because it is not a boolean`);
        }
        return expression.accept(this); // todo this seems duplicate, see two lines above
    }

    visitExpression(expression) {
        const leftHand = expression.getLeftHand();
        const rightHand = expression.getRightHand();
        const operator = expression.getOperator();

        const leftHandType = leftHand.accept(this);
        const rightHandType = rightHand.accept(this);

        if (!leftHandType || !rightHandType) {
            return leftHandType; // todo what's the purpose if this??
        } else {
            if (leftHandType.getType() !== rightHandType.getType()) {
                this.errors.push(`Invalid expression. The operator ${operator} can not be applied 
                                to ${leftHand.toString()} [type: ${leftHandType.toString()}] 
                                and ${rightHand.toString()}[type: ${rightHandType.toString()}]. Reason types are different`);
            }

            if (!leftHandType.isValidOperator(operator)) {
                this.errors.push(`Invalid expression. The operator ${operator} can not be applied
                            to ${leftHand.toString()} [type: ${leftHandType.toString()}] 
                            and ${rightHand.toString()}[type: ${leftHandType.toString()}]`);
            }
        }
        return expression.getType();
    }

    visitProperty(property) {
        let name = property.getName();

        // todo we need a way to check for non allocated properties for Answers and Expressions.

        /* Return QLBoolean for a reserved boolean name */
        if (this._reservedBooleanNames.includes(name)) {
            this.memoryState.set(name, this.memoryState.getType(name), property.getName()); // todo type is not set correctly
            return new QLBoolean();
        }

        this.memoryState.set(name, this.memoryState.getType(name)); // todo type is not set correctly
        return this.memoryState.getType(name);
    }

    /**
     * Check duplicate question declarations with different types
     * @param question
     */
    checkDuplicateDeclarations(question) {
        const property = question.getProperty();
        const memoryElement = this.memoryState.getElement(property);

        const propertyInMemory = memoryElement !== undefined;

        // TODO check if error should be thrown with a duplicate declaration with the same type
        if (propertyInMemory && question.getPropertyType() !== memoryElement.getType()) {
            this.warnings.push(`Property "${question.getPropertyType()}" is being used with multiple types`);
        }
    }

    checkDuplicateLabels(statement) {
        const localLabel = statement.getLabel();
        const label = find(this.labels, (label) => label.contains(localLabel));

        if (label) {
            this.warnings.push(`Label "${localLabel.getValue()}" is being used multiple times`);
        } else {
            this.labels.push(statement.getLabel());
        }
    }

    // todo naming?
    checkProperty(property){
        const memoryElement = this.memoryState.getElement(property);
        const propertyInMemory = memoryElement !== undefined;
        if(propertyInMemory){
            this.errors.push(`Property "${property.getName()}" is being used but is not defined.`)
        }
    }

    hasDetectedErrors() {
        return !!this.errors.length;
    }
}