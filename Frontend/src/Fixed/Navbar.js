import { AppBar } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import { useHistory } from 'react-router-dom/cjs/react-router-dom'
import './Navbar.css';

function Navbar(props) {

    const [authState,setAuthState]=useState();
    const [role,setRole]=useState()

    const history=useHistory()

    useEffect(()=>{
        setAuthState("token" in localStorage?true:false)
        setRole(localStorage.getItem("roles")==='"ADMIN"'?true:false)
  
    })

    const handleLogout=()=>{
        localStorage.clear()
        history.push("/login")
    }

  return (   
    <section>
    {authState==true?<nav className="navbar navbar-fixed-top navbar-expand-lg navbar-dark bg-dark">
    
    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>
  
 
      
    <div className="collapse navbar-collapse" id="navbarSupportedContent">
    
      
        {role==true&&<ul className="navbar-nav mr-auto  text-uppercase">
        
        <li className="nav-item">
        <a className="nav-link" href="/">Home</a>
        </li >
          <li className="nav-item dropdown">
          <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            ADD
          </a>
          <div className="dropdown-menu" aria-labelledby="navbarDropdown">
            <a className="dropdown-item" href="/StoreBatch">STORE DATA</a>
            <a className="dropdown-item" href="/EmployeeBatch">EMPLOYEE DATA</a>    </div>
        </li></ul>}
        
        {role===false&&<ul className="navbar-nav mr-auto  text-uppercase">
        <li className="nav-item">
        <a className="nav-link" href="/">Home</a>
        </li >
        <li className="nav-item dropdown">
          <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            UPDATE
          </a>
          <div className="dropdown-menu" aria-labelledby="navbarDropdown">
            <a className="dropdown-item" href="/StoreUpdate">STORE UPDATE</a>
            <a className="dropdown-item" href="/EmployeeUpdate">EMPLOYEE UPDATE</a> </div>
        </li>
        <li className="nav-item dropdown">
          <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            APPROVE
          </a>
          <div className="dropdown-menu" aria-labelledby="navbarDropdown">
            <a className="dropdown-item" href="/StoreRequest">STORE APPROVE</a>
            <a className="dropdown-item" href="/EmployeeRequest">EMPLOYEE APPROVE</a></div>
        </li>
        
        </ul>}

      
        <ul className="navbar-nav ms-auto my-2 my-lg-0">
        <li className="ms-auto my-lg-0 mx-2 text-white">
        {JSON.parse(localStorage.getItem('username'))}
        </li>
        <li>
        <button className="btn btn-outline-danger text-white bg-danger my-2 my-sm-0" onClick={handleLogout}>LOGOUT</button> </li>   </ul> 
    </div>
   
     

    </nav>:<></>}</section>
  )
  
}

export default Navbar
