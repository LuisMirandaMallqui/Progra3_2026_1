using Newtonsoft.Json;
using System;
using System.IO;
using System.Net;

namespace CSharpRestClient
{
    public class HttpClientUtils<T>
    {
        public T get(string url)
        {
            HttpWebRequest req = (HttpWebRequest)WebRequest.Create(url);
            req.Method = "GET";
            req.Accept = "application/json";
            req.Timeout = 30000;

            using (HttpWebResponse resp = (HttpWebResponse)req.GetResponse())
            using (StreamReader sr = new StreamReader(resp.GetResponseStream()))
            {
                string json = sr.ReadToEnd();
                if (typeof(T) == typeof(string)) return (T)(object)json;
                return JsonConvert.DeserializeObject<T>(json);
            }
        }

        public T post(string url, object data)
        {
            HttpWebRequest req = (HttpWebRequest)WebRequest.Create(url);
            req.Method = "POST";
            req.Accept = "application/json";
            req.ContentType = "application/json";
            req.Timeout = 30000;

            string jsonBody = JsonConvert.SerializeObject(data);
            using (StreamWriter sw = new StreamWriter(req.GetRequestStream()))
            {
                sw.Write(jsonBody);
                sw.Flush();
            }

            using (HttpWebResponse resp = (HttpWebResponse)req.GetResponse())
            using (StreamReader sr = new StreamReader(resp.GetResponseStream()))
            {
                string json = sr.ReadToEnd();
                if (typeof(T) == typeof(string)) return (T)(object)json;
                return JsonConvert.DeserializeObject<T>(json);
            }
        }

        public T put(string url, object data)
        {
            HttpWebRequest req = (HttpWebRequest)WebRequest.Create(url);
            req.Method = "PUT";
            req.Accept = "application/json";
            req.ContentType = "application/json";
            req.Timeout = 30000;

            string jsonBody = JsonConvert.SerializeObject(data);
            using (StreamWriter sw = new StreamWriter(req.GetRequestStream()))
            {
                sw.Write(jsonBody);
                sw.Flush();
            }

            using (HttpWebResponse resp = (HttpWebResponse)req.GetResponse())
            using (StreamReader sr = new StreamReader(resp.GetResponseStream()))
            {
                string json = sr.ReadToEnd();
                if (typeof(T) == typeof(string)) return (T)(object)json;
                return JsonConvert.DeserializeObject<T>(json);
            }
        }

        public T delete(string url)
        {
            HttpWebRequest req = (HttpWebRequest)WebRequest.Create(url);
            req.Method = "DELETE";
            req.Accept = "application/json";
            req.Timeout = 30000;

            using (HttpWebResponse resp = (HttpWebResponse)req.GetResponse())
            using (StreamReader sr = new StreamReader(resp.GetResponseStream()))
            {
                string json = sr.ReadToEnd();
                if (typeof(T) == typeof(string)) return (T)(object)json;
                return JsonConvert.DeserializeObject<T>(json);
            }
        }
    }
}
