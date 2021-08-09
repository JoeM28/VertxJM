package com.joe.udemy.vertx_starter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MethodRepo
{
  public MethodRepo printName(String name)
  {
    System.out.println("Name is " + name);
    return this;
  }
  public MethodRepo printAge(int Age)
  {
    System.out.println("Age is " + Age);
    return this;
  }

}

public class MethodchainingEx1 {

    public static void main(String[] args) {

    MethodRepo methodRepo1 = new MethodRepo();
    methodRepo1.printName("Joe");
    methodRepo1.printAge(35);
    methodRepo1.printName("JM").printAge(30);

    List<String> names = Arrays.asList("Apple", "Orange" , "Banana");

    Stream<String> names1 = names.stream();
    Stream<String> names2 = names1.map(e-> e + " Fruit");
    List<String> names3 = names2.collect(Collectors.toList());
    System.out.println("names1 = " + names1.toString() );
    System.out.println("names2 = " + names2.toString() );
    System.out.println("names3 = " + names3 );

    List<String> names4 = names.stream().map(e-> e + " Fruit").collect(Collectors.toList());

    System.out.println("names4 = " + names4 );

    List<String> customname = new ArrayList<>();
    for (String s : names)
    {
      String newName = s + " Fruit";
      customname.add(newName);
    }

    System.out.println("Before  ---> " + names);
    System.out.println("After   ---> " + customname );
  }
}
