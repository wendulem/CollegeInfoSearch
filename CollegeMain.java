import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import org.json.*;

public class CollegeMain
{
  private static JFrame new_frame;

  public static void main(String[] args) throws IOException
  {
    // TODO Auto-generated method stub
    new_frame = new CollegeGUI();
    new_frame.setVisible(true);

  }

  public static List<String> getCollegeData(String[] colleges)
    throws IOException
  {
    List<String> collegeFeedback = new ArrayList<>();
    for (int i = 0; i < colleges.length; i++)
    {
      StringBuilder result = new StringBuilder();
      String[] collegeNames = colleges[i].split(" ");
      String queryString =
        "https://api.data.gov/ed/collegescorecard/v1/schools?" + "school.name=";
      for (int j = 0; j < collegeNames.length; j++)
      {
        if (collegeNames.length > 1)
        {
          queryString += collegeNames[j] + "%20";
        }
        else
        {
          queryString += collegeNames[j];
        }
      }
      queryString +=
        "&school.degrees_awarded.predominant=2,3&_fields=id,school.name,2016.student.size,school.degree_urbanization,2016.admissions.admission_rate.overall,2016.admissions.sat_scores.average.overall&api_key=sYfXIxxImmXyrrjZhjordCAOP7gtRQiACcnFpq0Z";
      URL url = new URL(queryString);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setDoOutput(true);
      conn.connect();
      BufferedReader rd =
        new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = rd.readLine()) != null)
      {
        result.append(line);
      }
      rd.close();
      collegeFeedback.add(result.toString());
    }
    return collegeFeedback;
  }

  public static void createText() throws IOException
  {
    String[] collegeArray = new String[1];
    collegeArray[0] = ((CollegeGUI) new_frame).getInput();
    List<String> testOutput = getCollegeData(collegeArray);

    // Change the conditional statement to apply to the output quantity (size)
    for (int i = 0; i < 1; i++)
    {
      // This needs to be figured out because with multiple school, you would
      // need to find the proper indices (not zero)
      JSONObject obj = new JSONObject(testOutput.get(i));
      JSONArray pageName = obj.getJSONArray("results");
      JSONObject schoolFields = pageName.getJSONObject(0);
      String schoolName = schoolFields.getString("school.name");
      double acceptanceRate =
        (schoolFields.getDouble("2016.admissions.admission_rate.overall"))
          * 100;
      int studentBody = schoolFields.getInt("2016.student.size");
      JPanel currentPanel = ((CollegeGUI) new_frame).getPanel();
      currentPanel.add(new JLabel(schoolName + ": (2016)"));
      currentPanel.add(new JLabel(String.valueOf(acceptanceRate + "% Acceptance Rate")));
      currentPanel.add(new JLabel(String.valueOf(studentBody + " Students")));
      new_frame.pack();
    }
  }
}
