
import java.util.ArrayList;
import java.util.List;

// Step 1: Observer interface — all displays must implement this
interface Observer {
    void update(float temperature, float humidity);
}

// Step 2: Subject interface — the thing being observed (WeatherStation)
interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// Step 3: Concrete Subject — WeatherStation holds data + observer list
class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;

    public void addObserver(Observer o) {
        observers.add(o);
        System.out.println("[Station] Observer registered: " + o.getClass().getSimpleName());
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
        System.out.println("[Station] Observer removed: " + o.getClass().getSimpleName());
    }

    // Called automatically when data changes
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature, humidity);
        }
    }

    // When weather changes → notify everyone
    public void setWeather(float temperature, float humidity) {
        System.out.println("\n[Station] Weather changed → Temp: " + temperature + "°C, Humidity: " + humidity + "%");
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers();
    }
}

// Step 4: Concrete Observers — each display reacts in its own way
class MobileApp implements Observer {
    @Override
    public void update(float temperature, float humidity) {
        System.out.println("  [Mobile App]   Temp: " + temperature + "°C | Humidity: " + humidity + "% — Notification sent!");
    }
}

class Website implements Observer {
    @Override
    public void update(float temperature, float humidity) {
        System.out.println("  [Website]      Page refreshed → " + temperature + "°C, " + humidity + "% humidity");
    }
}

class LedDisplay implements Observer {
    @Override
    public void update(float temperature, float humidity) {
        System.out.println("  [LED Display]  Scrolling: " + temperature + "°C *** " + humidity + "% RH");
    }
}

// Step 5: Client
public class Weathermonitoring {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        Observer mobile = new MobileApp();
        Observer website = new Website();
        Observer led = new LedDisplay();

        station.addObserver(mobile);
        station.addObserver(website);
        station.addObserver(led);

       
        station.setWeather(32.5f, 78.0f);
        station.setWeather(28.0f, 65.0f);

        
        System.out.println("\n[Station] LED display going offline...");
        station.removeObserver(led);

        station.setWeather(35.0f, 80.0f);
    }
}

/