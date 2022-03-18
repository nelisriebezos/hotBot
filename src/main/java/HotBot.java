import application.HotBotService;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;

import java.time.LocalDate;
import java.util.*;

public class HotBot {
    public static void main(String[] args) {
        DiscordApi api = new DiscordApiBuilder()
                .setToken("OTU0NDg4MzAyMDg4NjMwMzMy.YjT2gA.4BEcUEJQ_AF1UZrV5x9m4U15vgw")
                .setAllIntents()
                .login().join();

        Collection<Server> serverCollection = api.getServers();
        Server server = serverCollection.iterator().next();
        HotBotService hotBotService = new HotBotService(server);

        System.out.println("Test");

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                LocalDate currentDate = LocalDate.now();
                hotBotService.dateCycler();

                if (hotBotService.getThursday().equals(currentDate)) {
                    hotBotService.saveAllUserDisplayNames();
                    hotBotService.changeAllDisplayNames("Party maruf");
                }
                if (hotBotService.getFriday().equals(currentDate)) {
                    hotBotService.revertAllDisplayNames();
                }
            }
        };
        timer.schedule(timerTask, 1, 10800000);
    }
}
