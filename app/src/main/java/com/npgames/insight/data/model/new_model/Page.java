package com.npgames.insight.data.model.new_model;

import com.npgames.insight.data.model.BlockArea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 05.11.2017.
 */

public class Page implements Serializable{
    public List<BlockArea> blocks;

    public Page() {
        blocks = new ArrayList<>();
    }
}
