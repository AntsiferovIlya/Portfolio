package main;

import main.model.DoList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {

    private static AtomicInteger currantId = new AtomicInteger(0);
    private static ConcurrentHashMap<Integer, DoList> doListHashMap = new ConcurrentHashMap<>();

    public static List<DoList> getAllList() {
        ArrayList<DoList> doList = new ArrayList<DoList>();
        doList.addAll(doListHashMap.values());
        return doList;
    }

    public static int addDoList(DoList doList) {
        int id = currantId.incrementAndGet();
        doListHashMap.put(id, doList);
        return id;
    }

    public static DoList getById(AtomicInteger doId) {
        if(doListHashMap.containsKey(doId)) return doListHashMap.get(doId);

        return null;
    }

    public static DoList updateToDo(int id, String newName, String newAbout) {
        if(doListHashMap.containsKey(id)) {
            DoList doList = doListHashMap.get(id);
            doList.setName(newName);
            doList.setAbout(newAbout);
            doListHashMap.put(id, doList);
            return doList;
        }
        return null;
    }

    public static void delList() {
        for(int i = 1; i < doListHashMap.size(); i++) {
            if(doListHashMap.containsKey(i)) {
                doListHashMap.remove(i);
                currantId.decrementAndGet();
            }
        }
    }

    public static void delById(int id) {
        if(doListHashMap.containsKey(id)) {
            doListHashMap.remove(id);
            currantId.decrementAndGet();
        }
    }

    public static int getSize() {
        return doListHashMap.size();
    }
}
