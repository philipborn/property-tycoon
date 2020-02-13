/*
 * MIT License
 *
 * Copyright (c) 2020 Tom Doran
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.watson.propert.tycoon.io;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.HashMap;

public class BoardReaderJson {

    private JSONArray objects;
    private int iterator;
    private String[] keys = {"Position", "Name", "Group", "Action", "canBeBought",
            "Cost", "Rent", "1house", "2houses", "3houses", "4houses", "hotel"};

    public BoardReaderJson(String fileName) {

        JSONParser jp = new JSONParser();
        iterator = 0;

        try {
            Object o = jp.parse(new FileReader(fileName));
            objects = (JSONArray) o;

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private int dataSize(){
        return objects.size();
    }

    private void getNext() {
        iterator++;
    }

    private HashMap<String, Object> getObjectData() {

        HashMap<String,Object> data = new HashMap<>();
        // get current JSONObject
        JSONObject jso = (JSONObject) objects.get(iterator);

        // build map of data
        for(String key : keys) {
            // if square has relevant fields (ie. no rent information for 'Go')
            if(jso.get(key) instanceof String && ((String) jso.get(key)).length() > 0) {
                data.put(key, jso.get(key));
            } else if(jso.get(key) instanceof Long) {   // otherwise if data is a number

                // numbers wrapped with Long, so output ints (can be other data type if necessary later)
                data.put(key, ((Long) jso.get(key)).intValue());
            }
        }
        return data;
    }

    public static void main(String[] args) {

        // update to read from resource folder
        BoardReaderJson r = new BoardReaderJson("C:/Users/User/Downloads/boardDataJSON.json");

        HashMap<String,Object> data = r.getObjectData();

        System.out.println(data.get("Name"));
        System.out.println(data.get("Cost"));
        r.getNext();
        data = r.getObjectData();

        System.out.println(data.get("Name"));
        r.getNext();
        data = r.getObjectData();
        System.out.println(data.get("Name"));
        System.out.println(data.keySet());
    }
}