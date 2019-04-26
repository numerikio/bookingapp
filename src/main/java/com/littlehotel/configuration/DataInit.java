package com.littlehotel.configuration;

import com.littlehotel.model.GuestService;
import com.littlehotel.model.Room;
import com.littlehotel.model.User;
import com.littlehotel.model.UserProfile;
import com.littlehotel.service.GuestServiceService;
import com.littlehotel.service.RoomService;
import com.littlehotel.service.UserProfileService;
import com.littlehotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
@PropertySource({
        "classpath:room_init.properties",
        "classpath:guestservice_init.properties",
        "classpath:login_data.properties"
})
public class DataInit {

    @Autowired
    private RoomService roomService;

    @Autowired
    private GuestServiceService guestServiceService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserService userService;

    @Autowired
    private Environment environment;

    private String[] namesUserProfile = {"ADMIN", "RECEPTION", "USER"};

    @PostConstruct
    private void init() {
        initUserProfiles();
        initDefaultUsers();
        initRooms();
        initGuestServices();
    }

    private void initUserProfiles() {
        for (String name : namesUserProfile
        ) {
            addProfileTypesDataToDB(name);
        }
    }

    private void initDefaultUsers() {
        addDefaultUserToDB("data.admin.name", "data.admin.email", "data.admin.password", "ADMIN");
        addDefaultUserToDB("data.reception.name", "data.reception.email", "data.reception.password", "RECEPTION");
    }

    private void initRooms() {
        addRoomDataToDB("data.room.100.name", "data.room.100.description", "data.room.100.category", "data.room.100.cost");
        addRoomDataToDB("data.room.101.name", "data.room.101.description", "data.room.101.category", "data.room.101.cost");
        addRoomDataToDB("data.room.102.name", "data.room.102.description", "data.room.102.category", "data.room.102.cost");
        addRoomDataToDB("data.room.200.name", "data.room.200.description", "data.room.200.category", "data.room.200.cost");
        addRoomDataToDB("data.room.201.name", "data.room.201.description", "data.room.201.category", "data.room.201.cost");
        addRoomDataToDB("data.room.202.name", "data.room.202.description", "data.room.202.category", "data.room.202.cost");
        addRoomDataToDB("data.room.300.name", "data.room.300.description", "data.room.300.category", "data.room.300.cost");
        addRoomDataToDB("data.room.301.name", "data.room.301.description", "data.room.301.category", "data.room.301.cost");
    }

    private void initGuestServices() {
        addGuestServiseToDB("data.guestservice.breakfast.name", "data.guestservice.breakfast.cost","data.guestservice.breakfast.regularity");
        addGuestServiseToDB("data.guestservice.spa.name", "data.guestservice.spa.cost","data.guestservice.spa.regularity");
        addGuestServiseToDB("data.guestservice.excursion.name", "data.guestservice.excursion.cost","data.guestservice.excursion.regularity");
        addGuestServiseToDB("data.guestservice.car.name", "data.guestservice.car.cost","data.guestservice.car.regularity");

    }

    private void addProfileTypesDataToDB(String type) {
        if (userProfileService.findByType(type) == null) {
            UserProfile userProfile = new UserProfile();
            userProfile.setType(type);
            userProfileService.saveUserProfile(userProfile);
        }
    }

    private void addDefaultUserToDB(String name, String email, String password, String userProfile) {
        if (userService.getUserByName(environment.getProperty(name)) == null) {
            User user = new User();
            user.setName(environment.getProperty(name));
            user.setEmail(environment.getProperty(email));
            user.setPassword(environment.getProperty(password));

            Set<UserProfile> userProfiles = new HashSet<>();
            userProfiles.add(userProfileService.findByType(userProfile));
            user.setUserProfiles(userProfiles);
            userService.saveUser(user);
        }
    }

    private void addRoomDataToDB(String name, String description, String category, String cost) {
        if (roomService.getRoomByName(environment.getProperty(name)) == null) {
            Room room = new Room();
            room.setNameOfRoom(environment.getProperty(name));
            room.setDescription(environment.getProperty(description));
            room.setCategory(environment.getProperty(category));
            room.setCost(Double.valueOf(environment.getProperty(cost)));
            roomService.saveRoom(room);
        }
    }

    private void addGuestServiseToDB(String name, String cost, String regularity) {
        if (guestServiceService.findByName(environment.getProperty(name)) == null) {
            GuestService guestService = new GuestService();
            guestService.setNameService(environment.getProperty(name));
            guestService.setCost(Double.valueOf(environment.getProperty(cost)));
            guestService.setRegularity(environment.getProperty(regularity));
            guestServiceService.saveGuestService(guestService);
        }
    }
}


