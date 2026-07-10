using System.Collections.Generic;

namespace PokemonWebViewer.Code.Utils;

public static class CSVUtils
{
    public static List<string> ParseCsvLine(string line)
    {
        var result = new List<string>();
        var currentField = new System.Text.StringBuilder();
        bool inQuotes = false;
        for (int i = 0; i < line.Length; i++)
        {
            char c = line[i];
            if (c == '"')
            {
                inQuotes = !inQuotes;
            }
            else if (c == ',' && !inQuotes)
            {
                result.Add(currentField.ToString());
                currentField.Clear();
            }
            else
            {
                currentField.Append(c);
            }
        }
        result.Add(currentField.ToString());
        return result;
    }
}
