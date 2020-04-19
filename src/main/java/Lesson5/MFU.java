package Lesson5;

public class MFU {

    final Object printLock = new Object();
    final Object scanLock = new Object();

    public void print(String doc, int pages) {
        synchronized (printLock) {
            System.out.printf("\nНачало печати документа %s (всего %d страниц) ...", doc, pages);
            try {
                for (int i = 0; i < pages; i++) {
                    Thread.sleep(1000);
                    System.out.printf("\nДокумент %s, страница %d из %d напечатана", doc, i + 1, pages);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("    Документ %s напечатан.", doc);
        }
    }

    public void scan(String doc, int pages) {
        synchronized (scanLock) {
            System.out.printf("\nНачало сканирования документа %s (всего %d страниц) ...", doc, pages);
            try {
                for (int i = 0; i < pages; i++) {
                    Thread.sleep(700);
                    System.out.printf("\nДокумент %s, страница %d из %d отсканирована", doc, i + 1, pages);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("    Документ %s отсканирован.", doc);
        }
    }

    public void copy(String doc, int pages) {
        synchronized (scanLock) {
            System.out.printf("\nНачало копирования документа %s (всего %d страниц) ...", doc, pages);
            try {
                for (int i = 0; i < pages; i++) {
                    Thread.sleep(700);
                    System.out.printf("\nКопирование: документ %s, страница %d из %d отсканирована", doc, i + 1, pages);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("   Копирование: сканирование %s завершено.", doc);
        }
        // после завершения этапа сканирования в операции "Копирование"
        // МФУ в принципе готов к началу следующего копирования или сканирования
        synchronized (printLock) {
            System.out.printf("\nКопирование: начало печати документа %s (всего %d страниц) ...", doc, pages);
            try {
                for (int i = 0; i < pages; i++) {
                    Thread.sleep(1000);
                    System.out.printf("\nКопирование: документ %s, страница %d из %d напечатана", doc, i + 1, pages);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("    Документ %s напечатан. Копирование завершено", doc);
        }
    }

    public static void main(String[] args) {
        final MFU mfu = new MFU();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.copy("Doc 1", 8);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Doc 2", 9);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Doc 3", 7);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("Doc 4", 12);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.copy("Doc 5", 6);
            }
        }).start();


    }

}