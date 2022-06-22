//package com.Boda.Yamyam.domain;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//public class MenuImage {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long menuImageId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "menu_id")
//    private Menu menu;
//
//    private String fileName;
//
//    private String filePath;
//
//}
