package Knowledge.Reflect;

public class Hebei extends China {
  private String name;
  public int population;
  public String location;

  public Hebei() {
    name = "Hebei";
    population = 200000000;
    location = "North";
  }

  public Hebei(String name, int population, String location) {
    this.name = name;
    this.population = population;
    this.location = location;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

  public int getPopulation() {
    return population;
  }

  public String getLocation() {
    return location;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("My name is ").append(name).append(", ");
    sb.append("I have ").append(population).append(" peoples, ");
    sb.append("and I am in ").append(location).append(" of China");
    sb.append(", and China is in ").append(continent).append(".");
    return sb.toString();
  }
}
