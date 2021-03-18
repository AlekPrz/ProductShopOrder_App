package com.app.service;

import java.util.Objects;

public class StuffInfoService {

    private final MyServiceImpl myService;

    public StuffInfoService(MyServiceImpl myService) {
        this.myService = myService;
    }


    final int printViewMenu() {
        System.out.println("Wybierz opcje");
        System.out.println("1 - Wyświetlenie produktu o największej cenie z każdej kategori");
        System.out.println("2 - Wyświetlenie produktów które zamawiane\n" +
                "były przez klientów pochodzących z kraju o nazwie podanej przez\n" +
                "użytkownika i wieku z przedziału określanego przez użytkownika.");
        System.out.println("3 - Wyświetlenie produktu o największej cenie z każdej kategori");
        System.out.println("4 - Wyświetlenie produktu o największej cenie z każdej kategori");

        return UserDataService.getInt();

    }

    public final void option1ViewMenu() {

        myService.getProducts3a().forEach(System.out::println);
    }


    public void option2ViewMenu() {

        System.out.println("Give the name");
        String name = UserDataService.takeString();

        System.out.println("Give the age min");
        String ageMin = UserDataService.takeString();
        System.out.println("Give the age max");
        String ageMax = UserDataService.takeString();

        myService.getProducts3b(name, Integer.parseInt(ageMin), Integer.parseInt(ageMax)).forEach(System.out::println);
    }
}
