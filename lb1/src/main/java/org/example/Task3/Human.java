package org.example.Task3;

import lombok.Data;

@Data
public class Human {
    private String name;
    private int strenght;

    public void enterRoom(Kamorka kamorka) {
        if (!kamorka.isOpened()) {
            System.out.println(name + " can't entered the kamorka");
            throw new IllegalArgumentException("Kamorka is closed");
        }
        System.out.println(name + " entered the kamorka");

    }

    public void pullHumanInKamorka(Human human,Kamorka kamorka) {
        if(strenght < human.strenght || !kamorka.isOpened()) {
            System.out.println(name + " can't pull in " + human.name);
            throw new IllegalArgumentException(name + " is weaker than " + human.name + " or kamorka is closed");
        }
        System.out.println(name + " pulled in " + human.name);
    }

    public void detectComputerEquipmentInKamorka(Kamorka kamorka) {
        if (kamorka.getComputerEquipment().getAmount() == Amount.Empty || !kamorka.isOpened()) {
            System.out.println("Human " + name + " can't detect computer equipment in kamorka");
            throw new IllegalArgumentException("Computer equipment is empty or kamorka is closed");
        }
        System.out.println( name + " detected " + kamorka.getComputerEquipment().getAmount() + " computer equipment");
    }

    public void setStrenght(int strenght) {
        if (strenght < 0) {
            throw new IllegalArgumentException("Strength cannot be negative");
        }
        this.strenght = strenght;
    }



}
