package marissaburca.TASK_TRACKER_BE;

import marissaburca.TASK_TRACKER_BE.entities.Avatar;
import marissaburca.TASK_TRACKER_BE.repositories.AvatarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainRunner implements CommandLineRunner {
    @Autowired
    private AvatarDAO avatarDAO;
    @Override
    public void run ( String... args ) throws Exception {

        Avatar avatar1 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345299/Avatars/m63il8h3uhewqnap83dx.webp");
        //avatarDAO.save(avatar1);
        Avatar avatar2 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345293/Avatars/xv9clfnxtx5e3bxyrs0l.webp");
        //avatarDAO.save(avatar2);
        Avatar avatar3 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345291/Avatars/botdmntf1t8q15xhmliy.webp");
        //avatarDAO.save(avatar3);
        Avatar avatar4 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345281/Avatars/o1eeiyqe9hvlejwbbv10.webp");
        //avatarDAO.save(avatar4);
        Avatar avatar5 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345272/Avatars/tv7kshunt24ejt4mm7ty.webp");
        //avatarDAO.save(avatar5);
        Avatar avatar6 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345264/Avatars/keivlh5g1kbhfylgt7bp.webp");
        //avatarDAO.save(avatar6);
        Avatar avatar7 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345256/Avatars/m8ornbz6oduwpe2byd0s.webp");
        //avatarDAO.save(avatar7);
        Avatar avatar8 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345245/Avatars/ijiynnykks6ebpnitini.webp");
        //avatarDAO.save(avatar8);
        Avatar avatar9 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345236/Avatars/clzndecc1znemvzvowhx.webp");
        //avatarDAO.save(avatar9);
        Avatar avatar10 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345226/Avatars/hlwbjakfydfi6j1mljmb.webp");
        //avatarDAO.save(avatar10);
        Avatar avatar11 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345213/Avatars/kv8c9ermgkoigiv8gr8r.webp");
        //avatarDAO.save(avatar11);
        Avatar avatar12 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345291/Avatars/botdmntf1t8q15xhmliy.webp");
        //avatarDAO.save(avatar12);
        Avatar avatar13 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345192/Avatars/kmhayozzkgkrx6o0klki.webp");
        //avatarDAO.save(avatar13);
        Avatar avatar14 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345180/Avatars/qgbwu2slz7hqywoz9iks.webp");
        //avatarDAO.save(avatar14);
        Avatar avatar15 =new Avatar("https://res.cloudinary.com/di0pzr05b/image/upload/v1707345168/Avatars/tr2m9lbexdo0m6rx5fys.webp");
        //avatarDAO.save(avatar15);
    }
}
