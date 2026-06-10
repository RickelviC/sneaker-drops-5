package com.pluralsight.sneakerdrops;


import com.pluralsight.sneakerdrops.data.*;
import com.pluralsight.sneakerdrops.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StartupRunner implements CommandLineRunner {
    private final SneakerRepository sneakerRepository;
    private final BrandRepository brandRepository;


    @Autowired
    public StartupRunner(SneakerRepository sneakerRepository, BrandRepository brandRepository) {
        this.sneakerRepository = sneakerRepository;
        this.brandRepository = brandRepository;

    }

    public void run(String... args) throws Exception {
        seedDate();
        for (Brand brand : brandRepository.findAll()) {
            System.out.println(brand.getId() + " - " + brand.getName());
        }
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n=== Game Library ===");
            System.out.println("1) List all sneakers");
            System.out.println("0) Quit");
            System.out.print("Choose: ");
            switch (scanner.nextInt()) {
                case 1 -> listSneakers();
                case 0 -> running = false;
                default -> System.out.println("Unknown option.");
            }
        }
    }

    private void seedDate() {
        if (brandRepository.count() == 0) {
            brandRepository.save(new Brand("Nike"));
            brandRepository.save(new Brand("Adidas"));
            brandRepository.save(new Brand("New Balance"));
            brandRepository.save(new Brand("Puma"));
            brandRepository.save(new Brand("ReeBok"));
        }
        if (sneakerRepository.count() == 0) {
            sneakerRepository.save(new Sneaker("nikes", 200, 2002));
            sneakerRepository.save(new Sneaker("Adidas", 120, 2012));
            sneakerRepository.save(new Sneaker("New Balance", 160, 2025));
            sneakerRepository.save(new Sneaker("Puma", 90, 2015));
            sneakerRepository.save(new Sneaker("ReeBok", 140, 2017));
        }
    }

    private void listSneakers() {
        System.out.println("You have " + sneakerRepository.count() + " Sneakers:");
        for (Sneaker sneaker : sneakerRepository.findAll()) {
            System.out.println(sneaker.getId() + " - " + sneaker.getModel() + " - " + sneaker.getReleaseYear() + " ($" + sneaker.getPrice()  + ")");
        }

    }
}