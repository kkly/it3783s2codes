package com.example.pract4;

import java.math.BigDecimal;

public class ProductDataManager {
    Product[] products = {
            new Product("AMAZONECHODOT", "Amazon Echo Dot",
                    "Echo Dot is a hands-free, voice-controlled device with a small built-in speaker-it can also connect to your speakers or headphones over Bluetooth.",
                    "Smart Home",
                    new BigDecimal(39.0), new BigDecimal(29.0),
                    R.drawable.amazon_echo),

            new Product("GOOGLECHROMECAST", "Google Chromecast",
                    "Stream your favourite entertainment to your TV with a Chromecast. Buy now. Easy setup. Automatic updates. Portable. Wi-Fi enabled.",
                    "Smart Home",
                    new BigDecimal(80.0), new BigDecimal(65.0),
                    R.drawable.google_chromecast),

            new Product("GOOGLEHOME", "Google Home",
                    "Use your voice to set alarms, create reminders & find answers. Buy now. Automatic updates. Control devices at home. Quick & easy to set up. Manage everyday tasks.",
                    "Smart Home",
                    new BigDecimal(149.0), new BigDecimal(129.0),
                    R.drawable.google_home),

            new Product("GOOGLEHOMEMINI", "Google Home Mini",
                    "Google Home Mini is Google Assistant anywhere you want it. Ask it questions. Tell it to do things. It's your own Google, always ready to help.",
                    "Smart Home",
                    new BigDecimal(75.0), new BigDecimal(50.0),
                    R.drawable.google_homemini),

            new Product("APPLEHOMEPOD", "Apple Homepod",
                    "HomePod is a breakthrough speaker with amazing sound, spatial awareness, Siri intelligence, and smart home control. ",
                    "Smart Home",
                    new BigDecimal(450.0), new BigDecimal(350.0),
                    R.drawable.apple_homepod),

            new Product("SONYPS4", "Sony Playstation 4",
                    "Meet the original PS4, delivering awesome gaming power that's always for the players.",
                    "Gaming Consoles",
                    new BigDecimal(800.0), new BigDecimal(699.0),
                    R.drawable.sony_ps4),

            new Product("MICROSOFTXBOXONE", "Microsoft XBox One",
                    "Play over 1,300 games on the only consoles designed to play the best games of the past, present, and future.",
                    "Gaming Consoles",
                    new BigDecimal(700.0), new BigDecimal(599.0),
                    R.drawable.microsoft_xbox),

            new Product("NINTENDOSWITCH", "Nintendo Switch",
                    "The Nintendo Switch™ system, a gaming console you can play both at home and on-the-go.",
                    "Gaming Consoles",
                    new BigDecimal(399.0), new BigDecimal(299.0),
                    R.drawable.nintendo_switch),


            new Product("BEATSHEADPHONES", "Beats Headphones",
                    "Powerful sound and audio technology from Beats by Dre.",
                    "Gaming Consoles",
                    new BigDecimal(399.0), new BigDecimal(299.0),
                    R.drawable.beats_headphones),

    };

    public ProductDataManager() {
    }



    public Product[] getProducts(){
        return this.products;
    }

    public Product getProductById(String id){
//        for(Product p : products){
//
//            if(p.getId().equals(id)){
//                return p;
//            }
//
//        }

        for(int i = 0; i < products.length; i++){
            if(products[i].getId().equals(id))
                return products[i];
        }

        return null;

    }
}
