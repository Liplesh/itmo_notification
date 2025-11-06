package ru.lipnin.notifyservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.lipnin.notifyservice.dto.GeneralNotificationDto;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class NotificationShedule {
    private final NotificationService notificationService;

    @Async("notifier-executor") // задачи будут выполняться отдельным пулом
    @Scheduled(fixedRate = 7, initialDelay = 1, timeUnit = TimeUnit.DAYS)
    public void sendNotificationForAll() {
        notificationService.sendNotification(new GeneralNotificationDto("Посетите наш салон"));
    }
}

/*@Service
public class SpringBootThread {

    @Async("xxx-executor")
    public CompletableFuture<String> *//*void*//* doInOtherThread(){
        System.out.println("doInOtherThread");
        return CompletableFuture.completedFuture("Done");
    }
}*/

// Использование
/*@RestController
class SomeService {
    @Autowired
    private SpringBootThread someThread;
    @GetMapping
    public void someVoidMethod(){
        someThread.doInOtherThread();
        CompletableFuture<String> future = someThread.doInOtherThread();
        future.thenAccept((stringResult)->{
            // инструкции будут выполнены,
            // когда метод doInOtherThread завершит работу

            // stringResult - результат работы метода
        });
        ///
    }
}*/