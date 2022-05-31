package Pointeuse;

import Pointeuse.Controller.PersonnShort;
import Pointeuse.View.Window;


public class Main {

    public static void main(String[] args) {

        new PersonnShort("Steve Rogers",1);
        new PersonnShort("Stan Lee",2);
        new PersonnShort("Tony Stark",3);



        Window w = new Window();
    }
}
