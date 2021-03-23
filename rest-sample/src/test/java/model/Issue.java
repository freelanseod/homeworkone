package model;

import java.util.Objects;

public class Issue {
    private int id;
    private String subject;
    private String description;
    private String stateName;
    private int state;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public String getStateName() {
        return stateName;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public int getState() {
        return state;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Issue withStateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public Issue withState(int state) {
        this.state = state;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id && state == issue.state && Objects.equals(subject, issue.subject) && Objects.equals(description, issue.description) && Objects.equals(stateName, issue.stateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, description, stateName, state);
    }

}
