package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class EasyStream {

    private List<Integer> list;

    private EasyStream(List<Integer> list) {
        this.list = new ArrayList<>(list);
    }

    public static EasyStream of(List<Integer> source) {
        return new EasyStream(source);
    }

    public EasyStream map(Function<Integer, Integer> fun) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, fun.apply(list.get(i)));
        }
        return new EasyStream(list);
    }

    public EasyStream filter(Predicate<Integer> fun) {
        list.removeIf(integer -> !fun.test(integer));
        return new EasyStream(list);
    }

    public List<Integer> collect() {
        return list;
    }

}
