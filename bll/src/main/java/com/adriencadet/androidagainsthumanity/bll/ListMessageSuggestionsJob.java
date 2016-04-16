package com.adriencadet.androidagainsthumanity.bll;

import android.content.Context;

import com.adriencadet.androidagainsthumanity.bll.utils.FinalWrapper;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * ListMessageSuggestionsJob
 * <p>
 */
class ListMessageSuggestionsJob {
    private Context context;

    Observable<Pair<List<String>, List<String>>> create() {
        return Observable
            .create(new Observable.OnSubscribe<Pair<List<String>, List<String>>>() {
                @Override
                public void call(Subscriber<? super Pair<List<String>, List<String>>> subscriber) {
                    String[] prefixes = context.getResources().getStringArray(R.array.prefixes);
                    String[] suffixes = context.getResources().getStringArray(R.array.suffixes);
                    List<Integer> prefixIndexes = new ArrayList<>(), suffixIndexes = new ArrayList<>();
                    int n = 4;
                    Pair<List<String>, List<String>> outcome;

                    while (n > 0) {
                        FinalWrapper<Integer> i = new FinalWrapper<>(-1), j = new FinalWrapper<>(-1);

                        while (i.get() == -1 || Stream.of(prefixIndexes).filter((e) -> e.equals(i.get())).count() != 0) {
                            i.set((int) Math.round(Math.random() * (prefixes.length - 1)));
                        }

                        while (j.get() == -1 || Stream.of(suffixIndexes).filter((e) -> e.equals(j.get())).count() != 0) {
                            j.set((int) Math.round(Math.random() * (suffixes.length - 1)));
                        }

                        prefixIndexes.add(i.get());
                        suffixIndexes.add(j.get());

                        n--;
                    }

                    outcome = new Pair<List<String>, List<String>>(
                        Stream.of(prefixIndexes).map((e) -> prefixes[e]).collect(Collectors.toList()),
                        Stream.of(suffixIndexes).map((e) -> suffixes[e]).collect(Collectors.toList())
                    );

                    subscriber.onNext(outcome);
                    subscriber.onCompleted();
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
