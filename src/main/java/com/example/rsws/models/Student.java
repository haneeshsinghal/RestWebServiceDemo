package com.example.rsws.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Model to create a new Student")
@Entity
@Table(name = "student")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated student ID")
    protected int id;
    @ApiModelProperty(notes = "Student first name",example = "Haneesh")
    @NotEmpty(message = "First Name is required")
    private String firstname;
    @ApiModelProperty(notes = "Student last name",example = "Singhal")
    @NotEmpty(message = "Last Name is required")
    private String lastname;
    @ApiModelProperty(notes = "Student email",example = "haneesh.singhal@gmail.com")
    @Column(nullable = true, name = "email")
    private String email;
    @ApiModelProperty(notes = "Student phone",example = "9810509724")
    @Column(nullable = true, name = "phone")
    private String phone;
}
