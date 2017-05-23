package bobberclobber.scramble_studios.se.samplingtimer.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.flotandroidchart.flot.FlotChartContainer;
import com.flotandroidchart.flot.FlotDraw;
import com.flotandroidchart.flot.Options;
import com.flotandroidchart.flot.data.PointData;
import com.flotandroidchart.flot.data.SeriesData;
import com.flotandroidchart.flot.data.TickData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import bobberclobber.scramble_studios.se.samplingtimer.R;

public class GraphActivity extends Activity {
    @SuppressWarnings("FieldCanBeLocal")
    private final String EXP_FILE_NAME = "exported-graph-data.txt";
    private final String X_VALUES_KEY = "x_values";
    private final String Y_VALUES_KEY = "y_values";

    // Stores the dates which are displayed on the x-axis
    private Vector<String> dateVector;

    // Stores the comments for each point
    private Vector<String> commentVector;

    //  A vector of data points, used in conjunction with graphSeries
    private Vector<PointData> pointVector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new GraphFragment())
                    .commit();
        }
    }

    // Read the graph data from a previously created file
    private void loadData() {
        // Get file data
        SharedPreferences pref = getPreferences(0);
        Set<String> xValues = pref.getStringSet(X_VALUES_KEY, new HashSet<String>());
        Set<String> yValues = pref.getStringSet(Y_VALUES_KEY, new HashSet<String>());

        // Load dates into the date vector
        for (String date : xValues)
            dateVector.add(date);

        // Load data points into the point vector
        int x = 0;
        for (String y : yValues)
            pointVector.add(new PointData(x++, Integer.parseInt(y)));

        // Add a single point in the origin if the point vector is empty
        if (x == 0)
            pointVector.add(new PointData(0, 0));

        // Display the graph
        updateGraph();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Initialize variables
        dateVector = new Vector<String>();
        pointVector = new Vector<PointData>();

        // Get previously saved data
        loadData();

        // Update the graph
        updateGraph();
    }

    // Re-attach the data series to the graph
    private void updateGraph() {
        // Vector storing all data series
        Vector<SeriesData> seriesVector = new Vector<SeriesData>();

        // Add the data points in the point vector to the graph
        SeriesData graphSeries = new SeriesData();
        graphSeries.setData(pointVector);
        seriesVector.add(graphSeries);

        // Used to draw the graphs
        FlotDraw fd = new FlotDraw(seriesVector, graphOptions(), null);

        // Gets the chart container and draws the graphs
        FlotChartContainer chartContainer = (FlotChartContainer) findViewById(R.id.chart_container);
        if (chartContainer != null)
            chartContainer.setDrawData(fd);
    }

    // Return an Options object which has been modified for this application's graph
    private Options graphOptions() {
        // Object for setting options
        Options options = new Options();

        // Set the dates in dateVector as the x-axis labels
        Vector<TickData> ticks = new Vector<TickData>();
        for (int x = 0; x < dateVector.size(); x++)
            ticks.add(new TickData(x, dateVector.get(x)));
        options.xaxis.ticks = ticks;
        options.xaxis.max = 20;
        options.xaxis.min = 0;

        // Set y-axis ticks
        options.yaxis.ticks = 10;
        options.yaxis.max = 10;
        options.yaxis.min = -10;

        // Set Background color
        options.canvas.fill = true;
        options.canvas.fillColor = new int[]{0x0099cc, 0x0099cc};

        options.grid.hoverable = true;

        // Show the graph as points connected by lines
        options.series.points.show = true;
        options.series.lines.setShow(true);

        return options;
    }

    // Takes a date and returns the month and the day in a string
    private String formatDate(Date date) {
        String[] tokens = date.toString().split(" ");
        return tokens[1] + " " + tokens[2];
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Get the preferences file
        SharedPreferences pref = getPreferences(0);
        SharedPreferences.Editor editor = pref.edit();

        // Reformat data so it can be stored in the preferences file
        Set<String> xValues = new HashSet<String>();
        Set<String> yValues = new HashSet<String>();
        for (String date : dateVector)
            xValues.add(date);
        for (PointData pointData : pointVector)
            yValues.add(Double.toString(pointData.y));

        // Add the data to the file
        editor.putStringSet(X_VALUES_KEY, xValues);
        editor.putStringSet(Y_VALUES_KEY, yValues);

        // Save changes
        editor.apply();
    }

    // TODO: Add comment input handling
    // Adds a new value to the graph
    public void onAddValueButtonClick(View view) {
        // Get the entered value
        EditText valueField = (EditText) findViewById(R.id.enter_value_field);
        String valueText = valueField.getText().toString();
        if (valueText.equals("")) {
            CharSequence msg = "You have to enter a value!";
            Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            // Parse the value text
            int value = Integer.parseInt(valueText);

            if (value < -10 || value > 10) {
                CharSequence msg = "That value is invalid!";
                Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
                toast.show();
            } else {
                // If no value has been manually added, remove the point in the origin
                if (dateVector.size() == 0)
                    pointVector.clear();

                // Add the new value
                pointVector.add(new PointData(pointVector.size(), value));

                // Add the current date to the date vector
                dateVector.add(formatDate(Calendar.getInstance().getTime()));

                // Update the graph with the new value
                updateGraph();
            }
        }
        // Clear the text field
        valueField.setText("");
    }

    // Exports the displayed data to a text file
    public void onExportDataButtonClick(View view) {
        // If an SD card is mounted correctly
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                // Get the directory "Sampling Timer", or create it if it doesn't exist
                File root = new File(Environment.getExternalStorageDirectory(), "Sampling Timer");
                if (!root.exists()) {
                    if (!root.mkdirs()) {
                        CharSequence msg = "An error occurred when exporting the data!";
                        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                    }
                }
                // Open a file in the directory with the name exported-graph-data.txt
                File gpxFile = new File(root, EXP_FILE_NAME);

                // Clear out any old content in the file
                PrintWriter clearer = new PrintWriter(gpxFile);
                clearer.close();

                // Write data to the file
                FileWriter writer = new FileWriter(gpxFile);
                writer.append(completeDataString());
                writer.flush();
                writer.close();
                CharSequence msg = "Data exported!";
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                CharSequence msg = "An error occurred when exporting the data!";
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

    // Builds a formatted string of all data which is appended to the export file
    private String completeDataString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pointVector.size(); i++) {
            builder.append(fString(dateVector.get(i), 6));
            builder.append(fString(Double.toString(pointVector.get(i).y), 6));
            builder.append(commentVector.get(i));
            builder.append("\n");
        }
        return builder.toString();
    }

    // Returns the given string with enough spaces appended to make it the given length
    private String fString(String str, int length) {
        int diff = length - str.length();
        String res = str;
        if (diff > 0)
            for (int i = 0; i < diff; i++)
                res += " ";
        return res;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.standard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    /**
     * A fragment used to display a graph
     */
    public static class GraphFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_graph, container, false);
        }
    }

    /**
     * A fragment used to display the comments
     */
    public static class CommentsFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_comments, container, false);
        }
    }
}
