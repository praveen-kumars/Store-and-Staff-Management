import './App.css';
import Login from './login/Login';
import { Route, BrowserRouter as Router,useHistory,Redirect,useLocation} from 'react-router-dom';
import Home from './Pages/Home/Home';
import StoreData from './Pages/BatchJob/StoreData';
import EmployeeData from './Pages/BatchJob/EmployeeData';
import StoreUpdate from './Pages/StoreUpdate/StoreUpdate';
import EmployeeUpdate from './Pages/EmployeeUpdate/EmployeeUpdate';
import StoreRequest from './Pages/RequestData/StoreRequest';
import EmployeeRequest from './Pages/RequestData/EmployeeRequest';
import React, { useEffect, useState} from 'react';
import Navbar from './Fixed/Navbar';
import { Switch } from 'react-router-dom/cjs/react-router-dom';
import { setSelectionRange } from '@testing-library/user-event/dist/utils';

function App(props) {
    const [authState,setAuthState]=useState()
    const history=useHistory()
    const handleLogout=()=>{
      localStorage.clear()
      history.push("/")
  }
    useEffect(()=>{
      setInterval(()=>{
          const token=localStorage.getItem('token'); 
          localStorage.getItem("token")!=null?setAuthState(true):setAuthState(false)
        },1000)
       /*localStorage.getItem("token")!=null?
      setTimeout(() => {
              handleLogout()
              console.log(localStorage.getItem("ExpirationTime")-localStorage.getItem("CurrentTime"))
        },(5000000)):setAuthState(false);
      ;}*/},[authState])
  return (
    <Router>
    
        <Navbar loggedIn={authState} />
       <Route exact path='/' component={Home}/>
       <PublicRoute path='/login' component={Login}/>
      <PrivateRoute path='/StoreBatch' component={StoreData}/>
      <PrivateRoute path='/EmployeeBatch' component={EmployeeData}/>
      <PrivateRoute path='/StoreUpdate' component={StoreUpdate}/>
      <PrivateRoute path='/EmployeeUpdate' component={EmployeeUpdate}/>
      <PrivateRoute path='/StoreRequest' component={StoreRequest}/>
      <PrivateRoute path='/EmployeeRequest' component={EmployeeRequest}/>
   
    
    </Router>
  );
  
}

function PrivateRoute({component,...rest}){

  return(
    <Route
       {...rest}
       render={(props)=>
        
        localStorage.token?
        (React.createElement(component,props)):
        (<Redirect
          to={{
            pathname:"/login",
            state:{
              from: props.location,
            }
          }}
          />)
      }
    />
  )
}

function PublicRoute({component,...rest}){
  return(
    <Route
       {...rest}
       render={(props)=>
        localStorage.token?
         (<Redirect
          to={{
            pathname:"/",
            state:{
              from: props.location,
            }
          }}
          />):
          (React.createElement(component,props))
      }
    />
  )
  ;
}


export default App;
