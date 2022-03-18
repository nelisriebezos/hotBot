package application;

import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;

public class HotBotService {
    HashMap<Long, String> userMap = new HashMap<Long, String>();
    Server server;
    LocalDate thursday;
    LocalDate friday;

    public HotBotService(Server server) {
        this.server = server;
    }

    public void dateCycler() {
        LocalDate today = LocalDate.now();
        thursday = today.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        friday = today.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
    }

    public void saveAllUserDisplayNames() {
        for (User user : server.getMembers()) {
            userMap.put(user.getId(), user.getDisplayName(server));
        }
    }

    public void changeAllDisplayNames(String nameToChangeTo) {
        for (User user : server.getMembers()) {
            user.updateNickname(server, nameToChangeTo);
        }
    }

    public void revertAllDisplayNames() {
        for (User user : server.getMembers()) {
            user.updateNickname(server, userMap.get(user.getId()));
        }
    }

    public void resetAllDisplayNames() {
        for (User user : server.getMembers()) {
            user.resetNickname(server);
        }
    }

    public LocalDate getThursday() {
        return thursday;
    }

    public LocalDate getFriday() {
        return friday;
    }
}
