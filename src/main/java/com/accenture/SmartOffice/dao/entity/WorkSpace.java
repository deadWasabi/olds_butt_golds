package com.accenture.SmartOffice.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "workspaces")
public class WorkSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "x_position")
    private Integer xPosition;
    @NotNull
    @Column(name = "y_position")
    private Integer yPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public Integer getxPosition() {
        return xPosition;
    }

    public void setxPosition(@NotNull Integer xPosition) {
        this.xPosition = xPosition;
    }

    @NotNull
    public Integer getyPosition() {
        return yPosition;
    }

    public void setyPosition(@NotNull Integer yPosition) {
        this.yPosition = yPosition;
    }

    @NotNull
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NotNull
    public Office getOffice() {
        return office;
    }

    public void setOffice(@NotNull Office office) {
        this.office = office;
    }


}
