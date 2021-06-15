package com.asgdx.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "task")
@SequenceGenerator(name="seq1",initialValue = 1000/*,allocationSize = 50*/)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq1")
    private Long id;
    private String name;
    private String description;
    private String status;
    private String dueTime;
    private Long empId;


    public Task(String name, String description, String status, String dueTime) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.dueTime = dueTime;
    }

    public Task(String name, String description, String status, String dueTime, Long empId) {
        this(name, description, status, dueTime);
        this.empId = empId;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(getId(), task.getId()) &&
                Objects.equals(getName(), task.getName()) &&
                Objects.equals(getDescription(), task.getDescription()) &&
                Objects.equals(getStatus(), task.getStatus()) &&
                Objects.equals(getDueTime(), task.getDueTime()) &&
                Objects.equals(getEmpId(), task.getEmpId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getStatus(), getDueTime(), getEmpId());
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", dueTime='" + dueTime + '\'' +
                ", empId=" + empId +
                '}';
    }
}
