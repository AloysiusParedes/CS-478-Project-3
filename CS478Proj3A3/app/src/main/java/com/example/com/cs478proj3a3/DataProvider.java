package com.example.com.cs478proj3a3;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    public static List<String> listNBA = new ArrayList<String>();
    public static List<String> sitesNBA = new ArrayList<String>();
    public static List<String> listMLB = new ArrayList<String>();
    public static List<String> sitesMLB = new ArrayList<String>();

    static {
        //add top 6 NBA
        listNBA.add("Golden State Warriors");
        listNBA.add("San Antonio Spurs");
        listNBA.add("Los Angeles Clippers");
        listNBA.add("Cleveland Cavaliers");
        listNBA.add("Toronto Raptors");
        listNBA.add("Oklahoma City Thunder");

        sitesNBA.add("http://www.nba.com/warriors/");
        sitesNBA.add("http://www.nba.com/spurs/");
        sitesNBA.add("http://www.nba.com/clippers/");
        sitesNBA.add("http://www.nba.com/cavaliers/");
        sitesNBA.add("http://www.nba.com/raptors/?splash=off");
        sitesNBA.add("http://www.nba.com/thunder/");

        //add top 6 MLB
        listMLB.add("Chicago Cubs");
        listMLB.add("Washington Nationals");
        listMLB.add("Toronto Blue Jays");
        listMLB.add("Texas Rangers");
        listMLB.add("Cleveland Indians");
        listMLB.add("Boston Red Sox");

        sitesMLB.add("http://chicago.cubs.mlb.com/");
        sitesMLB.add("http://washington.nationals.mlb.com/");
        sitesMLB.add("http://toronto.bluejays.mlb.com/");
        sitesMLB.add("http://texas.rangers.mlb.com/");
        sitesMLB.add("http://cleveland.indians.mlb.com/");
        sitesMLB.add("http://boston.redsox.mlb.com/");
    }

}
