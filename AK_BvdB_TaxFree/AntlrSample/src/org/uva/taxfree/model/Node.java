package org.uva.taxfree.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Node {

    private Node mParent;
    private Set<Node> mChildren;

    public Node() {
        mChildren = new LinkedHashSet<>(); ///< preserves the order in which the items were inserted
    }

    public void addChild(Node child) {
        mChildren.add(child);
    }

    public void retrieveQuestions(Set<NamedNode> set) {
        addQuestion(set);
        for (Node child : mChildren) {
            child.retrieveQuestions(set);
        }
    }

    protected void addQuestion(Set<NamedNode> set) {
        // Intentionally left blank
    }

    public void printData() {
        printValue();
        for (Node child : mChildren) {
            child.printValue();
        }
    }

    public void setVisibility(boolean isVisible) {
        for (Node child : mChildren) {
            child.setVisibility(isVisible);
        }
    }

    public void printValue() {
        // Intentionally left blank
    }

    public String getType() {
        return this.getClass().toString();
    }

    public abstract String toString();

    public void printAll() {
        System.out.println(toString());
        for(Node child : mChildren){
            child.printAll();
        }
    }

    public void retrieveConditions(Set<Node> set) {
        addCondition(set);
        for (Node child : mChildren) {
            child.retrieveConditions(set);
        }
    }

    protected void addCondition(Set<Node> set) {
        // Intentionally left blank
    }

    public Set<Node> getConditionsV2() {
        Set<Node> nodeSet = new LinkedHashSet<>();
        for (Node n : mChildren) {
            nodeSet.addAll(n.getConditionsV2());
        }
        nodeSet.addAll(addCondition());

//        By checking with a method that we need to override
        if (isCondition()) {
            nodeSet.add(this);
        }
//        By calling a method that we need to override
//        nodeSet.addAll(addCondition());
        return nodeSet;
    }

    protected boolean isCondition() {
        return true;
    }

    protected Set<Node> addCondition() {
        return Collections.emptySet();
    }
}