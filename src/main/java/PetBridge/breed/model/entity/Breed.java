package PetBridge.breed.model.entity;

import jakarta.persistence.*;

@Entity
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String imagePath;
}