package com.company;

import java.util.ArrayList;

/**
 * This will contain all of the records of names of enemies/items etc.. which will be pulled from
 * at random when creating these objects
 */
public class DB_  implements java.io.Serializable {

    private Dice d1 = new Dice();
    private Religion religion = new Religion();


    /**
            =================================== INGREDIENTS =================================
                                                                                                   */

    /**
     * Healing
     */
    public Ingredient glowing_nettle = new Ingredient("Glowing Nettle", "A nettle which emits a strange," +
            " bright sheen.", 0.01, 15, IngredientType.HEALING_HERB);
    public Ingredient comfrey_root = new Ingredient("Comfrey Root", "An extremely" +
            " rare plant with healing properties.", 0.005, 54, IngredientType.HEALING_HERB);
    public Ingredient ember_flowers = new Ingredient("Ember Flowers", "Scarcely found, and beautiful in " +
            "appearance - filled with healing properties.", 0.004, 46, IngredientType.HEALING_HERB);


    /**
     * Creature Part
     */
    public Ingredient rat_tail = new Ingredient("Rat's Tail", "Extremely popular in ritualistic alchemy, " +
            "rat's tails are widely used in various potions.", 0.019, 8, IngredientType.CREATURE_PART);
    public Ingredient troll_skull = new Ingredient("Troll Skull","The remains of a long passed Troll, whomever ran " +
            "into this beast must not have made it out without a scratch.",2.2,25, IngredientType.CREATURE_PART);
    public Ingredient wold_pelt = new Ingredient("Wolf Pelt","The pelt of a wolf, skinned by a hunter.",1.67,
            24, IngredientType.CREATURE_PART);
    public Ingredient horse_hide = new Ingredient("Horse Hide","From a horse that has perhaps served it's purpose",
            3.20, 30, IngredientType.CREATURE_PART);
    public Ingredient fox_claws = new Ingredient("Fox Claws","Smaller claws than most you see in this " +
            "place. Thankfully.",0.23, 10, IngredientType.CREATURE_PART);
    public Ingredient bear_fat = new Ingredient("Bear Fat","Scavenged from a black bears remains.", 0.21,
            29, IngredientType.CREATURE_PART);
    public Ingredient boar_tusk = new Ingredient("Boar Tusk","Sharp silver tusk.", 0.14, 13,
            IngredientType.CREATURE_PART);


    /**
     * Unique
     */
    public Ingredient graymold = new Ingredient("Graymold","Slimy and dark gray mold, most often found " +
            "underground - particularly potent for healing remedies.",0.025, 40, IngredientType.UNIQUE_HERB);
    public Ingredient mandrake = new Ingredient("Mandrake","This root is teeming with supernatural energy " +
            "which, properly treated, can be used to create powerful concoctions.",0.0014, 70, IngredientType.UNIQUE_HERB);
    public Ingredient bunchweed = new Ingredient("Bunchweed","Common weed found near water, distinct for it's whitened stem.",
            0.007, 13, IngredientType.UNIQUE_HERB);
    public Ingredient toadstools = new Ingredient("Toadstools","One variety of poisonous mushroom",0.03,
            12, IngredientType.UNIQUE_HERB);


    /**
     * Spiritual
     */
    public Ingredient viruscea = new Ingredient("Viruscea","Tall, bamboo-esque stalked flowers - cultivated by many farmers " +
            "due to their scent driving off wild animals... allegedly.", 0.086, 8, IngredientType.SPIRITUAL_HERB);
    public Ingredient holly = new Ingredient("Holly","Small, green, thorned plant with red berries - " +
            "popular in naturalistic rituals and alchemy.",0.003, 20, IngredientType.SPIRITUAL_HERB);
    public Ingredient crypt_shrooms = new Ingredient("Crypt Shrooms","Rare type of mushroom.",0.034,
            17, IngredientType.SPIRITUAL_HERB);
    public Ingredient asptongue_mold = new Ingredient("Asptongue Mold","Hard to find, and dangerous mold.",
            0.05, 23, IngredientType.SPIRITUAL_HERB);


    /**
     * Energetic
     */
    public Ingredient eyk_de_velle_blossom = new Ingredient("Eyk De Velle Blossom","A scarcely found plant " +
            "only found in the neighbouring region, particulary on hills.", 0.0023, 45, IngredientType.ENERGETIC_HERB);
    public Ingredient tsuebell = new Ingredient("Tsuebell","Large purple flower with an amber hue lining the edges.",
            0.003, 29, IngredientType.ENERGETIC_HERB);
    public Ingredient ashblossom = new Ingredient("Ashblossom","Bright red flower with a yellow-ish center, " +
            "found in humid environments",0.002, 10,IngredientType.ENERGETIC_HERB);
    public Ingredient wormgrass = new Ingredient("Wormgrass","Has a harsh looking appearance, defends itself " +
            "with numerous sharp edges.", 0.003, 14, IngredientType.ENERGETIC_HERB);

    /**
     * Materials
     */
    public Ingredient dram_soot = new Ingredient("Dram Soot","Soot gathered from items forged in Dram. Particularly " +
            "shiny compared to regular soot.", 0.023, 49, IngredientType.MATERIAL);
    public Ingredient dram = new Ingredient("Dram","Dark grey metal, used largely for forging domestic items due to it's " +
            "high heat and rust tolerance.", 0.06, 20, IngredientType.MATERIAL);
    public Ingredient cadmia = new Ingredient("Cadmia", "An oxide of zinc, usually found on the " +
            "sides of furnaces where copper or brass has been smelted.", 0.02, 7, IngredientType.MATERIAL);
    public Ingredient chalk = new Ingredient("Chalk", "Rock composite of calcium carbonate.", 0.02,
            3, IngredientType.MATERIAL);


    /**
     * Magics
     */
    public Ingredient tess_essence = new Ingredient("Tess Essence","Magical essence which was scattered across the " +
            "region's landscape after a priests tower nearby suffered an explosive event.", 0.0, 32, IngredientType.MAGIC);
    public Ingredient kohllden = new Ingredient("Kohllden","Metallic in appearance, gaseous in touch. Magical energy surrounds this anomaly.",
            0.34, 103, IngredientType.MAGIC);
    public Ingredient ghostly_matter = new Ingredient("Ghostly Matter","Remains from a reanimated spirit.",
            0.000,23, IngredientType.MAGIC);

    /**
     * Foods
     */
    public Ingredient anise = new Ingredient("Anise", " Anise seeds contain a form of plant estrogen " +
            "- the aromatic tea made from them deepens meditation and focus.",0.0012, 20, IngredientType.FOOD);
    public Ingredient basil = new Ingredient("Basil","A deep green, mint flavoured herb that is best used raw.", 0.000023, 14,
            IngredientType.FOOD);
    public Ingredient caraway_seed = new Ingredient("Caraway Seed","Some use these for protection and to dispel negative energy.",
            0.0042, 37, IngredientType.FOOD);
    public Ingredient cinnamon = new Ingredient("Cinnamon","Regarded as sacred by Dionysus, it is thought to increase spirituality " +
            " and play a role in medicinal ointments.", 0.0, 45, IngredientType.FOOD);
    public Ingredient cloves = new Ingredient("Cloves","Dried flower buds of a coastal tree, cloves are said to help dispel illusions " +
            "and are a potent pain reliever.", 0.0018, 29, IngredientType.FOOD);
    public Ingredient lemon = new Ingredient("Lemon","Lemons are an effective cleaning agent and are used for healing the body of " +
            "toxins.",0.0046, 34, IngredientType.FOOD);




    /**
     * * *
     * ------------> RANDOM LOOT ITEMS <------------------- *
                                                        * * *
                                                            */
    // Regular names
    private String[] prefix_name = {"Eustace","Vance","Denier","Molk","Voorg","Pik",
            "Roister","Gujg","Enlett","Cisz","Mayvent","Alenn","Alain","Joreg",
            "Mance","Mace","Ivan","Dusty","Ik","Dijkstra","Enigma","Leon",
            "Mosk","Enneruil","Iban","Clohsk","Tyvin","Mann","Yvin","Rex","Molly",
            "Lod","Mangey","Fox","Kreeg","Adane"};
    // 'The Butcher' , 'Doc' , 'Deranged' etc..
    private String[] prefix_title = {"The King","'Valiant'","'Smiley'","'Big Iron'","'Defeater'"};


    // 'The terrible' , 'Crusher' etc..
    private String[] middle_nickname = {"'Crusher'","'Terrible'","'Callous'","'Poison'","'Rat'",
            "'Skeever'","'Double-Blade'","'Bone-Crusher'","'Skull-Taker'","'Soul-Warden'","'Death-Bringer'",
            "'The One-Armed Slayer'","'The Inn-Murderer'","'Black-Wolf'","'The Sinner'","'Hangman'",
            "'The Red Town Executor'","'Meathook'","'Lockjaw'","'Hammer-Fist'","'The 4th Assassin'",
            "'Brutal'","'Lucifer'","'Rascal'","'Hook'","'Scar'","'Cull'","'Vendetta'"};
    // 'to' , 'of' , 'from' etc..
    private String[] middle_connector = {"of","from","the Killer of","the Butcher of","the Bitch of",
            "the Vampire of",", Demon of",", Pillager of", ", Knight of",", Paladin of",", the Horror of",
            ", Destroyer of"};

    // 'The Killer' , 'The Maniacal' ,
    private String[] lastname_title = {"the Butcher","the Pious","the Deranged","the Small",
            "the Righteous","the Fallible","the Vain","the Drunk","the Venomous","the Malevolent",
            "the Marked","the Fatal","the Terrible","the Manslayer","the Slayer","the Killer","the Maniacal," +
            "the Callous","the Wicked","the Hateful","the Damnable","the Meek","the Cruel",
            "the Rich","the Man","the Frail","the Detestable","the Putrid"};
    // Place names
    private String[] lastname_place = {"Innespire","Duvalle","Solace Harbor","Rees Village",
            "Frugael Castle","Phantaemiel","Unn De Messvin","Sarre e Mieqew","Red Town","Arimor",
            "Seegil Rock","Port Fontrass","Deep Wood Pass","Ytrium Woods","Voodoo Valley",
            "Priests End Forest","Cauvin","Pontar","East Duntine","Quaillsesce","Mann eveille",
            "Misty Redoubt","Castle Strong","Vermuir","Phaedminn","Riverside Manor","Derren Lake",
            "Ken Far Village","Loot Town","East Hestwick","Miruine"};
    // Just lastnames
    private String[] lastname = {"Roche", "Gaul","Stone","Vandran","Greed",
            "Dunsville","de Vier","Dubrakc","Grave","Mileui","Annsbuer","Eltt"
            ,"Vorrg","Uyk","Ulyrik","Dredd","Folst","Mez","Flynt","Doe","Roz",
            "deRidder","Meiuvide","Malrog","Qionze","Harraway","Kotsch-Konner",
            "Devil"};


    /** Combinations:
     * Generate a random enemy name and return it
     */
    public String generate_enemy_name() {
        String name = "";

        int combination = d1.manualDiceRoll(8);

        switch (combination) {
            case 1:
                name = d1.random_enemy_name(prefix_name) + " " + d1.random_enemy_name(lastname);
                break;
            case 2:
                name = d1.random_enemy_name(prefix_name)
                        + " " + d1.random_enemy_name(middle_connector)
                        + " " + d1.random_enemy_name(lastname_place);
                break;
            case 3:
                name = d1.random_enemy_name(prefix_name)
                        + " " + d1.random_enemy_name(middle_nickname)
                        + " " + d1.random_enemy_name(lastname);
                break;
            case 4:
                name = d1.random_enemy_name(prefix_name)
                        + " " + d1.random_enemy_name(lastname_title);
                break;
            case 5:
                name = d1.random_enemy_name(prefix_title);
                break;
            case 6:
                name = d1.random_enemy_name(prefix_title)
                        + " " + d1.random_enemy_name(lastname);
                break;
            case 7:
                name = d1.random_enemy_name(lastname);
                break;
            case 8:
                name = d1.random_enemy_name(lastname)
                        + " " + d1.random_enemy_name(middle_connector)
                        + " " + d1.random_enemy_name(lastname_place);
                break;
            default:
                generate_enemy_name();
        }
        return name;
    }

    public int random_loot_chance(int max) {
        return d1.manualDiceRoll(max);
    }

    /**
     * * *
     * ------------> RANDOM LOOT ITEMS <------------------- *
                                                        * * *
                                                            */

    public Altar return_random_altar() {

        Altar[] altars = new Altar[9];

        Altar altar_abel = new Altar("A depiction of a man, looking curiously at another who is making some kind of offering - a lamb on the fire.",Alignment.GOD, religion.getAbel());
        Altar altar_regular = new Altar("Next to a broken harp, an angel tends to her wounds.",null, null);
        Altar altar_regular2 = new Altar("A child attempts to look behind a great mirror, struggles greatly.",null, null);
        Altar altar_regular3 = new Altar("The Temple of Artemis basks in the morning sun, inhabitants scurry around it's great pillars.",Alignment.GREEKS, null);
        Altar altar_matthew = new Altar("A devout man sitting in a luxurious bath is fed wine, a more devout man supplies the water he cannot afford to keep",Alignment.GOD, religion.getMatthew());
        Altar altar_regular4 = new Altar("",null, null);
        Altar altar_hermes = new Altar("A winged man moves swiftly around a small globe.", Alignment.GREEKS, null);
        Altar altar_cain = new Altar("One makes a sacrificial offering in toil, another watches - pondering", Alignment.GOD, religion.getCain());
        Altar altar_regular5 = new Altar("A group of people leading livestock, point towards the distance at a bright light above.. rain approaches", null, null);


        altars[0]=(altar_abel);
        altars[1]=(altar_matthew);
        altars[2]=(altar_regular);
        altars[3]=(altar_regular2);
        altars[4]=(altar_regular3);
        altars[5]=(altar_regular4);
        altars[6]=(altar_hermes);
        altars[7]=(altar_cain);
        altars[8]=(altar_regular5);

        return altars[(d1.manualDiceRoll(altars.length))-1];

    }

    public Armour return_random_armour_item() {

        //ArrayList<Armour> armours = new ArrayList<>();
        Armour[] armours = new Armour[14];

        Armour tunic = new Armour("Tunic", "Torn cloth rags, these won't protect against anything.", 1.2, 3,0, 1, 0.00,false, true, new ItemType[]{ItemType.ABSORBS});
        Armour std_leather = new Armour("Studded Leather Body", "Tough animal hide, crafted for protection.", 2.4, 20, 3,10, 1.025, false, true, new ItemType[]{ItemType.ABSORBS});
        Armour bronze_body = new Armour("Bronze Plated Body Armour", "Forged bronze armour, made for battle.", 4.75, 45, 5,30, 1.28, false, true, new ItemType[]{ItemType.SHAPES});
        Armour sheepskin_hat = new Armour("Sheepskin Hat", "Provides some protection, against the wind", 0.9, 6, 1,3, 1.012, true, false, new ItemType[]{ItemType.ABSORBS});
        Armour iron_chain_body = new Armour("Iron Chain-Body", "Iron link armour - mitigates effects of attacks from sharp weapons", 2.7, 34, 5, 24, 1.07, false, true, new ItemType[]{ItemType.SHAPES});
        Armour bronze_helmet = new Armour("Bronze Helm", "War helmet, cast in bronze", 2.1, 23, 4, 17, 1.085, true, false, new ItemType[]{ItemType.SHAPES});
        Armour wynswood_bark_cuirass = new Armour("Wynswood-Bark Cuirass", "Tough bark, native to the region - fastened into a sturdy armour", 3.2, 38, 5,28, 1.027, false, true, new ItemType[]{ItemType.ABSORBS});
        Armour hunter_leather_coat = new Armour("Hunter's Leather Coat", "Designed for a rough life in the wilderness", 1.8, 12, 2, 8, 1.03, false, true, new ItemType[]{ItemType.ABSORBS});
        Armour mail_coif = new Armour("Mail Coif", "Chain link headress, protecting from dangerous slashes", 1.9, 21, 3, 17, 1.05, true, false, new ItemType[]{ItemType.ABSORBS});
        Armour plate_brigandine = new Armour("Plated Brigandine", "Tough, battle-ready armour for withstanding the throes of war", 5.2, 56, 6, 38, 1.32, false, true, new ItemType[]{ItemType.SHAPES});
        Armour scale_aventail = new Armour("Scale Aventail", "Scaled armour piece safeguards the neck and head", 1.7, 16, 2, 12, 1.055, true, false, new ItemType[]{ItemType.ABSORBS});
        Armour troll_hide_gambeson = new Armour("Troll Hide Gambeson", "Coated armour made from resilient troll's hide", 2.8, 30, 4, 30, 1.015, false, true, new ItemType[]{ItemType.ABSORBS});
        Armour iron_helmet = new Armour("Iron Helmet", "Cast Iron head armour, durable and rigid", 2.0, 29, 5, 23, 1.065, true, false, new ItemType[]{ItemType.SHAPES});
        Armour wynswood_bark_antlered_helm = new Armour("Wynswood-Bark Antlered Helm", "War helmet with giant antlers, made from Wynswood-Bark. Robust and long-lasting.", 1.9, 30, 4, 30, 1.15, true, false, new ItemType[]{ItemType.ABSORBS});

        armours[0]=(tunic);
        armours[1]=(std_leather);
        armours[2]=(bronze_body);
        armours[3]=(sheepskin_hat);
        armours[4]=(iron_chain_body);
        armours[5]=(bronze_helmet);
        armours[6]=(plate_brigandine);
        armours[7]=(scale_aventail);
        armours[8]=(mail_coif);
        armours[9]=(wynswood_bark_antlered_helm);
        armours[10]=(wynswood_bark_cuirass);
        armours[11]=(hunter_leather_coat);
        armours[12]=(troll_hide_gambeson);
        armours[13]=(iron_helmet);

        return armours[(d1.manualDiceRoll(armours.length))-1];

    }


    public Item return_random_healing_item() {

        Item[] healing_items = new Item[5];

        Item rags = new Item("Rags", "Sufficient for a desparate situation.",0.11,
                6, true,3,new ItemType[]{ItemType.ABSORBS,ItemType.WRITABLE,ItemType.SPLITS,ItemType.POUCH});
        Item bandage = new Item("Bandage", "Simple bandage for healing wounds", 0.12,
                20, true, 3,new ItemType[]{ItemType.ABSORBS,ItemType.WRITABLE,ItemType.SPLITS,ItemType.POUCH});
        Item salve = new Item("Salve", "Particularly potent healing ointment", 0.06,
                35, true, 5,new ItemType[]{ItemType.NA});
        Item tourniquet = new Item("Tourniquet", "Provides more healing than a common bandage", 0.18,
                28, true, 6,new ItemType[]{ItemType.ABSORBS,ItemType.WRITABLE,ItemType.POUCH});
        Item sage = new Item("Sage", "...", 0.03, 34, true,
                5,new ItemType[]{ItemType.NA});

        healing_items[0]=rags;
        healing_items[1]=bandage;
        healing_items[2]=sage;
        healing_items[3]=salve;
        healing_items[4]=tourniquet;

        return healing_items[(d1.manualDiceRoll(healing_items.length))-1];

    }

    public Weapon return_random_weapon() {

        Weapon[] weapons = new Weapon[16];

        Weapon steel_dagger = new Weapon("Steel Dagger", AttackType.STAB, 1.4, 4, new ItemType[]{ItemType.CUTTER});
        Weapon bronze_s_sword = new Weapon("Bronze Short-Sword", AttackType.SLASH, 1.8, 5, new ItemType[]{ItemType.CUTTER});
        Weapon steel_s_sword = new Weapon("Steel Short-Sword", AttackType.SLASH, 1.95, 6, new ItemType[]{ItemType.CUTTER});
        Weapon iron_flail = new Weapon("Iron Flail", AttackType.CRUSH, 2.24, 6, new ItemType[]{ItemType.HITS});
        Weapon stone_cudgel = new Weapon("Stone Cudgel", AttackType.CRUSH, 3.89, 6, new ItemType[]{ItemType.HITS});
        Weapon wooden_club = new Weapon("Wooden Club", AttackType.CRUSH, 1.34, 5, new ItemType[]{ItemType.HITS});
        Weapon iron_falchion = new Weapon("Iron Falchion",  AttackType.SLASH,1.75, 5, new ItemType[]{ItemType.CUTTER});
        Weapon steel_tanto = new Weapon("Steel Tanto", AttackType.SLASH, 1.65, 6, new ItemType[]{ItemType.CUTTER});
        Weapon steel_long_sword = new Weapon("Steel Long-Sword", AttackType.SLASH, 2.15, 7, new ItemType[]{ItemType.CUTTER});
        Weapon bone_shiv = new Weapon("Bone Shiv",  AttackType.STAB,1.25, 3, new ItemType[]{ItemType.CUTTER});
        Weapon bronze_falchion = new Weapon("Bronze Falchion", AttackType.SLASH, 1.60, 5, new ItemType[]{ItemType.CUTTER});
        Weapon iron_mace = new Weapon("Iron Mace", AttackType.CRUSH, 2.24, 6, new ItemType[]{ItemType.HITS});
        Weapon bronze_mace = new Weapon("Bronze Mace", AttackType.CRUSH, 2.16, 5, new ItemType[]{ItemType.HITS});
        Weapon steel_falchion = new Weapon("Steel Falchion", AttackType.SLASH, 2.25, 7, new ItemType[]{ItemType.CUTTER});
        Weapon bone_cudgel = new Weapon("Bone Cudgel",  AttackType.CRUSH,2.45, 5, new ItemType[]{ItemType.HITS});
        Weapon iron_pickaxe = new Weapon("Iron Pickaxe", AttackType.STAB, 3.50, 3, new ItemType[]{ItemType.CUTTER});

        weapons[0]=(steel_dagger);
        steel_dagger.setDescription("Well burnished dagger with a small diamond pommel at the foot of the hilt.");
        weapons[1]=(bronze_s_sword);
        bronze_s_sword.setDescription("Slightly rusted bronze sword - straight cross-guard and leather-wrapped hilt.");
        weapons[2]=(steel_s_sword);
        steel_s_sword.setDescription("A staple of many Knights, milled steel fashioned into a 24 inch point with a weighted cross-guard");
        weapons[3]=(iron_falchion);
        iron_falchion.setDescription("Worn iron blade, angled by the finest smiths into an atypical profile.");
        weapons[4]=(iron_flail);
        iron_flail.setDescription("Vicious spiked ball, attached to a wooden handle by a linked chain.");
        weapons[5]=(stone_cudgel);
        stone_cudgel.setDescription("Short, heavy one-handed club");
        weapons[6]=(wooden_club);
        wooden_club.setDescription("As it sounds, a wooden club.");
        weapons[7]=(steel_tanto);
        steel_tanto.setDescription("Prominent Japanese design, a short but effective blade.");
        weapons[8]=(steel_long_sword);
        steel_long_sword.setDescription("Steel blade, weighted cross-guard, formed into a short point at the summit.");
        weapons[9]=(bone_shiv);
        bone_shiv.setDescription("These makeshift weapons tend to be created after an horrific event.");
        weapons[10]=(bronze_falchion);
        bronze_falchion.setDescription("Rusted and worn blade nonetheless expertly forged, once gleaming and flashy.");
        weapons[11]=(iron_mace);
        iron_mace.setDescription("Weighty spiked section atop an iron shaft.");
        weapons[12]=(bronze_mace);
        bronze_mace.setDescription("Bronzed shaft with a deadly spike at the end, worn cloth tied to the hilt.");
        weapons[13]=(steel_falchion);
        steel_falchion.setDescription("Nitid curvaceous weapon with an extremely sharp edge, hints of rust dotted along it.");
        weapons[14]=(bone_cudgel);
        bone_cudgel.setDescription("Unnatural short and stocky cudgel.");
        weapons[15]=(iron_pickaxe);
        iron_pickaxe.setDescription("Regular usage would find this tool in the hands of many a prospector.");

        return weapons[(d1.manualDiceRoll(weapons.length))-1];

    }

    public Item return_random_valuable_item() {

        Item[] valuable_items = new Item[11];

        Item brooch = new Item("Silver Brooch", "An aristocrat's adornment.", 0.14,
                120, false, 0,new ItemType[]{ItemType.NA});
        Item sapphire = new Item("Sapphire", "A precious gem.", 0.21,
                145, false, 0,new ItemType[]{ItemType.SPLITS});
        Item warrant = new Item("Royal Warrant", "The Baron hereby declares, the party in possession of this warrant is to be granted a temporary easement within (certain place)",
                0.03, 160, false, 0,new ItemType[]{ItemType.PAPYRUS,ItemType.WRITABLE,ItemType.SPLITS});
        Item antique_plate = new Item("Antique Plate", "Very expensive dining equipment.", 0.19,
                115, false, 0,new ItemType[]{ItemType.SPLITS});
        Item amethyst = new Item("Amethyst Gem", "Piercing purple glow emanates from this gem.", 0.11,
                235, false, 0,new ItemType[]{ItemType.SPLITS});
        Item inscribed_ring = new Item("Inscribed Ring", "The inscription has faded over time, though the value of this artefact will have risen",
                0.06, 95, false, 0,new ItemType[]{ItemType.NA});
        Item painted_jug = new Item("Painted Jug", "Perhaps an ancient ritualistic ceremony item..", 0.25,
                190, false, 0,new ItemType[]{ItemType.CONTAINER,ItemType.SPLITS});
        Item stitched_cloth = new Item("Stitched Vicuna Cloth", "Extremely rare cloth, sought after by even the richest of individuals",
                0.05, 300, false, 0,new ItemType[]{ItemType.ABSORBS,ItemType.WRITABLE,ItemType.POUCH});
        Item gniousch_wine = new Item("Gniousch Wine", "High-Brow wine, consumed by the upper class.", 0.7,
                135, false, 0,new ItemType[]{ItemType.SOAKS});
        Item antique_coin = new Item("Antique Coin", "Etched with the image of Plutus.", 0.08,
                180, false, 0,new ItemType[]{ItemType.NA});
        Item emerald = new Item("Emerald", "Bright polished jewel", 0.10, 180,
                false, 0,new ItemType[]{ItemType.SPLITS});


        valuable_items[0]=(brooch);
        valuable_items[1]=(sapphire);
        valuable_items[2]=(warrant);
        valuable_items[3]=(antique_plate);
        valuable_items[4]=(amethyst);
        valuable_items[5]=(inscribed_ring);
        valuable_items[6]=(painted_jug);
        valuable_items[7]=(stitched_cloth);
        valuable_items[8]=(gniousch_wine);
        valuable_items[9]=(antique_coin);
        valuable_items[10]=(emerald);

        return valuable_items[(d1.manualDiceRoll(valuable_items.length))-1];

    }

    public Shield return_random_shield() {

        Shield[] shield_items = new Shield[5];

        Shield steel_round_shield = new Shield("Steel Round Shield", "A sturdy shield made from steel.", 2.3,
                38, 4, 20, 8.0);
        Shield bronze_buckler = new Shield("Bronze Buckler", "Small and reliable bronze shield.", 1.85,
                22, 2, 15, 2.5);
        Shield wynswood_kite_shield = new Shield("Wynswood Kite-Shield", "Resilient Wynswood fashioned into a kite-shield",  2.5,
                46, 6, 30, 9.5);
        Shield iron_kite_shield = new Shield("Iron Kite Shield", "Relaible kite-shield, made from iron.", 2.3,
                30, 3, 18, 4.0);
        Shield iron_buckler = new Shield("Iron Buckler", "Small iron buckler, great for movement.", 1.9,
                32, 4, 15, 4.5);

        shield_items[0]=(steel_round_shield);
        shield_items[1]=(bronze_buckler);
        shield_items[2]=(iron_kite_shield);
        shield_items[3]=(iron_buckler);
        shield_items[4]=(wynswood_kite_shield);

        return shield_items[(d1.manualDiceRoll(shield_items.length))-1];
    }

    public Item generate_random_normal_item() {

        Item[] items_list = new Item[15];

        Item vial = new Item("Vial","Glass vial for holding liquid solution samples.", 0.12,
                12, false, 0,new ItemType[]{ItemType.CONTAINER,ItemType.SPLITS});
        Item scrap_of_paper = new Item("Scrap of Paper","Mostly for scribbling notes.", 0.004,
                5, false, 0,new ItemType[]{ItemType.PAPYRUS,ItemType.WRITABLE,ItemType.SPLITS});
        Item knife = new Item("Dull Knife","A used knife with a dulled edge, still capable of cutting " +
                "relatively fragile items. Not quite sharp enough to draw blood.", 0.21, 3, false, 0, new ItemType[]{ItemType.CUTTER});
        Item cloth = new Item("Cloth","Used widely for common clothing and washing, also for lining " +
                "armoured plates.", 0.002, 14, false, 0,new ItemType[]{ItemType.SPLITS,ItemType.ABSORBS,ItemType.WRITABLE,ItemType.FUEL});
        Item tea = new Item("Tea","A small, metallic container of tea leaves.", 0.09,
                20, false, 0,new ItemType[]{ItemType.SOAKS});
        Item fork = new Item("Fork","What's a fork doing here?",0.093,
                2, false, 0,new ItemType[]{ItemType.CUTTER});
        Item wooden_bowl = new Item("Wooden Bowl","Belongs in a kitchen.", 0.11,
                4, false, 0,new ItemType[]{ItemType.FUEL,ItemType.SPLITS,ItemType.CONTAINER,ItemType.WRITABLE});
        Item lockpick = new Item("Lockpick","This should ease the process of kleptomania, as long as you don't get caught.",0.016,
                14, false, 0,new ItemType[]{ItemType.NA});
        Item saw = new Item("Saw", "Very useful tool in specific scenarios.",0.32,
                19, false, 0,new ItemType[]{ItemType.CUTTER});
        Item hammer = new Item("Hammer","For bending and shaping metal",1.4,
                23, false, 0,new ItemType[]{ItemType.HITS});
        Item basket = new Item("Basket","Accessible storage, or for decoration.",1.1,
                22, false, 0,new ItemType[]{ItemType.POUCH,ItemType.SPLITS});
        Item candlestick = new Item("Candlestick","This won't provide too much light, better to get a torch for exploring ruins.",0.084,
                4, false, 0,new ItemType[]{ItemType.FLAME,ItemType.FUEL});
        Item torch = new Item("Torch","Emanates a large glow, lighting up your immediate surroundings. A necessary tool for traversing dangerous dungeons and routes.", 0.14,
                20, false, 0,new ItemType[]{ItemType.FLAME,ItemType.FUEL});
        Item quill = new Item("Quill", "Writing tool made from a moulted flight feather, some surfaces can be written on, and perhaps there are things better remembered.", 0.085,
                26, false, 0, new ItemType[]{ItemType.WRITES,ItemType.SPLITS});
        Item inkwell = new Item("Inkwell","small jar made of pewter, used for holding ink in a place convenient for the person who is writing.",0.011,
                31, false, 0, new ItemType[]{ItemType.SOAKS,ItemType.CONTAINER});


        items_list[0]=(vial);
        items_list[1]=(scrap_of_paper);
        items_list[2]=(cloth);
        items_list[3]=(knife);
        items_list[4]=(tea);
        items_list[5]=(fork);
        items_list[6]=(wooden_bowl);
        items_list[7]=(lockpick);
        items_list[8]=(saw);
        items_list[9]=(hammer);
        items_list[10]=(basket);
        items_list[11]=(candlestick);
        items_list[12]=(torch);
        items_list[13]=(quill);
        items_list[14]=(inkwell);

        return items_list[(d1.manualDiceRoll(items_list.length))-1];

    }

    public Item generate_food_item() {

        Item[] food_items = new Item[6];

        Item apple = new Item("Apple","A small red apple.",0.08,
                9, true, 3,new ItemType[]{ItemType.SPLITS});
        Item peach = new Item("Peach","Freshly ripened peach.",0.04,
                13, true, 5,new ItemType[]{ItemType.SPLITS});
        Item half_loaf = new Item("Half Loaf","Common half loaf of rye bread.", 0.17,
                20, true, 6,new ItemType[]{ItemType.ABSORBS,ItemType.SPLITS});
        Item pjioure_fruit = new Item("Pjioure Fruit","White fruit encased in green leaves - delicacy found in the surrounding regions.",
                0.04, 17, true, 4,new ItemType[]{ItemType.SPLITS});
        Item venison = new Item("Vension","Raw venison meat, full of fat and protein.",1.18,
                30, true, 10,new ItemType[]{ItemType.ABSORBS,ItemType.SPLITS});
        Item apple_pie = new Item("Apple pie","This has been sitting out for some time, but it's still edible.",
                1.33, 24, true, 7,new ItemType[]{ItemType.SPLITS});

        food_items[0]=(apple);
        food_items[1]=(peach);
        food_items[2]=(half_loaf);
        food_items[3]=(pjioure_fruit);
        food_items[4]=(venison);
        food_items[5]=(apple_pie);

        return food_items[(d1.manualDiceRoll(food_items.length))-1];
    }


}
