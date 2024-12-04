public class Zoo {
  public static void main(String[] args) {


      Habitat h1 = new Habitat("savannah", "400m^2", "28 degree");
      Habitat h2 = new Habitat("rain forest", "400m^2", "25 degree");
      Habitat h3 = new Habitat("tropical forest", "400m^2", "24 degree");
      Habitat h4 = new Habitat("ocean", "500m^2", "-30 degree");
      Staff s1 = new Staff("John", "feeder", h1);
      Staff s2 = new Staff("Mark", "feeder", h2);
      Staff s3 = new Staff("Ben", "feeder", h3);
      Staff s4 = new Staff("Simon", "feeder", h4);
      Zoo zoo = new Zoo();
      zoo.addAnimal1("lion", "large cat", 5, h1);
      zoo.addAnimal2("tiger", "large cat", 6, h2);
      zoo.addAnimal3("monkey", "primate", 3, h3);
      zoo.addAnimal4("penguin", "bird", 4, h4);


s1.assignStaff();


  }
}




class Animal {
   private String name;
   private String species;
   private int age;
   private Habitat habitat;




   public Animal(String name, String species, int age, Habitat habitat) {
       this.name = name;
       this.species = species;
       this.age = age;
       this.habitat = habitat;


   }


   public String getName() {
       return name;
   }


   public String getSpecies() {
       return species;
   }


   public int getAge() {
       return age;
   }


   public Habitat getHabitat() {
       return habitat;
   }
}


class Habitat {
   private String type;
   private String size;
   private String temperature;




   public Habitat(String type, String size, String temperature) {
       this.type = type;
       this.size = size;
       this.temperature = temperature;
   }
   public String getType(){
   return type;
   }
   public String getSize(){
       return size;
   }
   public String getTemperature(){
       return temperature;
   }
}




class Zoo {
   private Animal a1;
   private Animal a2;
   private Animal a3;
   private Animal a4;




   void addAnimal1(String name, String species, int age, Habitat habitat) {
       a1 = new Animal(name, species, age, habitat);
   }


   void addAnimal2(String name, String species, int age, Habitat habitat) {
       a2 = new Animal(name, species, age, habitat);
   }


   void addAnimal3(String name, String species, int age, Habitat habitat) {
       a3 = new Animal(name, species, age, habitat);
   }


   void addAnimal4(String name, String species, int age, Habitat habitat) {
       a4 = new Animal(name, species, age, habitat);
   }
}


   class Staff {
       private String name;
       private String role;
       private Habitat assignedHabitat;




       public Staff(String name, String role, Habitat assignedHabitat) {
           this.name = name;
           this.role = role;
           this.assignedHabitat = assignedHabitat;
       }




  public void assignStaff() {
      System.out.println(name + " is assigned to " + assignedHabitat.getType() + " habitat.");
       }
   }
