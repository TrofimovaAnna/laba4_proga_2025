// Main.java
import korotyshky.*;
import threeObjects.*;
import material.Material;
import recordCor.Coordinates;
import exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import interfaces.Targetable;


public class Main {
    public static void main(String[] args) {
        new Main().runScenario();
    }

    public void runScenario() {
        System.out.println("Игра началась");

        Ball ball = new Ball(Material.RUBBER);
        Mirror mirror = new Mirror("Кривое зеркало", Material.GLASS, new Coordinates(3, 2));
        Stage stage = new Stage("Помост", Material.WOOD, new Coordinates(5, 4));
        Artist artist = new Artist("Артист", 0, new Coordinates(5, 5));

        Neznaika neznaika = new Neznaika("Незнайка", 80, ball);
        Kozlik kozlik = new Kozlik("Козлик", 75);


        // Проверка целостности (демонстрация FairException)
        try {
            if (mirror == null || stage == null || artist == null) {
                throw new FairException("Не все объекты ярмарки созданы!");
            }
        } catch (FairException e) {
            System.err.println("Ошибка в проверяемом исключении " + e.getMessage());
            return;
        }

        // Требование ТЗ: ArrayList для однотипных объектов
        List<Targetable> targets = new ArrayList<>();
        targets.add(artist);
        targets.add(stage);
        targets.add(mirror);

        boolean neznaikaSatisfied = false;
        boolean kozlikSatisfied = false;

        Random rand = new Random();
        int turn = 1;

        while (true) {
            boolean neznaikaAlive = !neznaika.isDead();
            boolean kozlikAlive = !kozlik.isDead();
            boolean artistAlive = !artist.isDead();

            // Проверка насыщения
            if (neznaikaAlive && !neznaikaSatisfied && neznaika.getHungerLevel() <= 5) {
                neznaikaSatisfied = true;
                System.out.println("Незнайка больше не голоден, он ушёл гулять по ярмарке!");
            }
            if (kozlikAlive && !kozlikSatisfied && kozlik.getHungerLevel() <= 5) {
                kozlikSatisfied = true;
                System.out.println("Козлик больше не голоден, он ушёл гулять по ярмарке!");
            }

            // === УСЛОВИЯ ЗАВЕРШЕНИЯ ===

            // 1. ПОБЕДА: оба насытились
            if (neznaikaSatisfied && kozlikSatisfied) {
                System.out.println("\nИгра закончилась. Оба героя сыты и счастливы!");
                break;
            }

            // 2. ПОРАЖЕНИЕ: оба погибли
            if (!neznaikaAlive && !kozlikAlive) {
                System.out.println("\nИгра закончилась. Оба героя погибли.");
                break;
            }

            // 3. ЧАСТИЧНЫЙ ФИНАЛ: оба завершили участие
            boolean neznaikaDone = !neznaikaAlive || neznaikaSatisfied;
            boolean kozlikDone = !kozlikAlive || kozlikSatisfied;
            if (neznaikaDone && kozlikDone) {
                System.out.println("\nИгра закончилась. Один герой погиб или насытился, другой завершил участие.");
                break;
            }

            System.out.println("\nход " + turn);

            // === ДЕЙСТВИЯ ===

            // Козлик смотрится в зеркало (если жив, не насытился и зеркало цело)
            if (kozlikAlive && !kozlikSatisfied && !mirror.isBroken()) {
                kozlik.lookIntoMirror(mirror);
            }

            // Незнайка бросает мяч (если жив и не насытился)
            if (neznaikaAlive && !neznaikaSatisfied) {
                Targetable target = targets.get(rand.nextInt(targets.size()));
                neznaika.throwBall(target);
            }

            // === СМЕРТЬ ОТ СОБЫТИЙ ===

            // Козлик умирает от горя (разбитое зеркало)
            if (kozlikAlive && !kozlikSatisfied && mirror.isBroken()) {
                kozlik.die();
                System.out.println("Козлик увидел разбитое зеркало, он умер от горя!");
            }

            // === ИЗМЕНЕНИЕ ГОЛОДА С ОБРАБОТКОЙ UNCHECKED ИСКЛЮЧЕНИЯ ===

            // У Незнайки — автоматический рост голода
            if (neznaikaAlive && !neznaikaSatisfied) {
                try {
                    neznaika.changeHungerLevel(1);
                } catch (LowEnergyException e) {
                    System.out.println("АВАРИЯ у Незнайки: " + e.getMessage());
                    neznaika.die();
                }
            }

            // У Козлика — голод меняется ТОЛЬКО через зеркало (обработка внутри lookIntoMirror)
            // Поэтому отдельного блока для auto-голода нет

            turn++;
            if (turn > 60) {
                System.out.println("\nИгра длится слишком долго");
                break;
            }

            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        }
    }

    // Вспомогательный метод для красивого вывода статуса героя
    private String getStatus(Korotyshka hero, boolean satisfied) {
        if (hero.isDead()) {
            return "мёртв";
        } else if (satisfied) {
            return "больше не голоден и ушёл";
        } else {
            return "голоден";
        }
    }
}