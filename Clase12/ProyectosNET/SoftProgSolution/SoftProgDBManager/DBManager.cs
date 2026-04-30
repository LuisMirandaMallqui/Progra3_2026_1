namespace SoftProgDBManager
{
    public class DBManager
    {
        private static DBManager _instance;
        private readonly string _connectionString;
        private DBManager(){ 
        
        } 
        
        private DBManager(string connectionString){
            _connectionString = connectionString;              
        }

        public static void Initialize(string connectionString)
        {
            if (_instance == null)
            {
                _instance = new DBManager(connectionString);
            }
        }

        public static SoftProgDBManager Instance
        {
            
        }

        public MySqlConnection GetConnection() { 
            return new MySqlConnection(_connectionString);
        }
    }
}
