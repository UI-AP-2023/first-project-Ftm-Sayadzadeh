package com.example.onlineshop.exceptions;

abstract public class DuplicateEntry extends Exception{
    public DuplicateEntry(String subClassMassage) {
        super("DuplicateEntry" + " - " + subClassMassage);
    }
}
