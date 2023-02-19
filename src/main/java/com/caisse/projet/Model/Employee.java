package com.caisse.projet.Model;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

  /*  private int empNo;
    private String name;
    private int salary;
    private float commission;*/

    private int id;
    private String name;
    private String designation;
    private double salary;
    private String doj;

}
