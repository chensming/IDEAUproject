package homework.week13;


class TaskPay{
    double pay = 0;
    String  name;

    public synchronized String getPay() throws InterruptedException{
        while(this.pay == 0){
            this.wait();
        }
        String payment  = name + "拿到了" + String.valueOf(pay);
        pay = 0;
        return payment;
    }

    public synchronized void givePay(String name, double pay){
        this.name = name;
        this.pay = pay;
        this.notifyAll();
    }
}


class WorkerThread extends Thread{
    TaskPay personPay;

    public WorkerThread(TaskPay personPay){
        this.personPay = personPay;
    }

    @Override
    public void run() {
        while(!isInterrupted()){
            String operation;
            try{
                operation = personPay.getPay();
            }catch (InterruptedException e){
                System.out.println("发生异常");
                break;
            }
            String result = operation;
            System.out.println(result);
        }
    }
}


public class Pay{
    public static void main(String args[]) throws InterruptedException {
        TaskPay personPay = new TaskPay();
        WorkerThread worker = new WorkerThread(personPay);
        worker.start();

        personPay.givePay("huawei",5682.36);
        Thread.sleep(1000);
        personPay.givePay("lianxiang",6954.25);
        Thread.sleep(1000);
        personPay.givePay("zhongxin",4563.24);

        worker.interrupt();
        worker.join();
        System.out.println("end main");
    }
}