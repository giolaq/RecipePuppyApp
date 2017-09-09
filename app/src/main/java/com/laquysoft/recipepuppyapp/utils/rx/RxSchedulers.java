package com.laquysoft.recipepuppyapp.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by joaobiriba on 08/09/2017.
 */

public interface RxSchedulers {


    Scheduler runOnBackground();

    Scheduler io();

    Scheduler compute();

    Scheduler androidThread();

    Scheduler internet();



}
