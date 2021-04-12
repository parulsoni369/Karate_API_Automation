function fn() {    
  var env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);

  if (!env) {
    env = 'SIT';
  }
  karate.configure("ssl",true)

  var HostUrl = 'https://api.openweathermap.org/data/2.5/'
  var OpenWeatherData = read('../../../Data/OpenWeatherMapData/OpenWeatherData.json')
  var ExpectedData = read('../../../Data/ExpectedResponseData/ExpectedData.json')

  if (env == 'SIT')
  {
     var config ={
      		env: env,
      		HostUrl : HostUrl,
      		OpenWeatherData : OpenWeatherData,
      		ExpectedData : ExpectedData
      		}
  }

  return config;
}