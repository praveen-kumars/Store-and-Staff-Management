import axios from 'axios'
import {React,useState,useEffect} from 'react'
import './EmployeeUpdate.css'
import{
  Grid,
  Button,
  TextField,
  FormControlLabel,
  Snackbar,
  Card,
  CardContent,
  CardAction,
  MenuItem,
  FormControl,
  Typography
} from "@material-ui/core"
import { Modal, ModalBody } from 'react-bootstrap'
import Alert from '@material-ui/lab/Alert'
import { show } from 'react-modal/lib/helpers/ariaAppHider'

function EmployeeUpdate() {
  const [data,setData]=useState({})
  const [requestData,setRequestData]=useState({})

  const [formData,setFormData]=useState({})

  const [employeeName,setEmployeeName]=useState()
  const [showForm,setShowForm]=useState()

  const [positionDisabled,setPositionDisabled]=useState(true)
  const [emailDisabled,setEmailDisabled]=useState(true)
  const [officeNumDisabled,setOfficeNumDisabled]=useState(true)
  const [phoneNumDisabled,setPhoneNumDisabled]=useState(true)
  const [deptAreaDisabled,setDeptAreaDisabled]=useState(true)
  const [error, setError] = useState({staff_Id:undefined});
  const [dataForm, setDataForm] = useState({
    staff_Idvalid:false,
  });
  const [isOpen, setIsOpen] = useState(false);

  const [snackbarMessage,setSnackBarMessage]=useState()
    const [snackbarSeverity,setSnackbarSeverity]=useState()
    const [showSnackbar,setSnackbar]=useState()

    const handleCloseSnackbar=(event,reason)=>{
            setSnackBarMessage('')
            setSnackbarSeverity('')
            setSnackbar(false)
    };

  const showModal = () => {
    setIsOpen(true);
  };

  const hideModal = () => {
    setIsOpen(false);
    setShowForm(false)
  };



  const fetchData=()=>{
    //console.log(storeName);
    if(!dataForm.staff_Idvalid){
      return
    }
    const token=JSON.parse(localStorage.getItem('token'))
    axios.get(`http://localhost:8012/employeeupdate/retrieve/${employeeName}`,{
      headers:{'Authorization':`Bearer ${token}`}
    })
    .then((response)=>{
      setData(response.data)
      setFormData(response.data)
      setShowForm(true)
      console.log(data)
     
      //console.log(response.data.store_Id)
      console.log(formData)
    })
    .catch((err)=>{
      setShowForm(false)
      setSnackBarMessage('Data Not Found')
      setSnackbarSeverity('error')
      setSnackbar(true)
    })
  }

  

  function handleChange(e){
    e.preventDefault()
    setEmployeeName(e.target.value)
  }

  const handleFormChange=(event)=>{
    event.preventDefault()
    setFormData({
        ...formData,
        [event.target.name]:event.target.value!==""?event.target.value : null
    })
}

/*  const handleEditClick=(event)=>{
    setDisabled(!disabled)
  }*/

  const clickHandler=param=>event=>{
    if(param==formData.phone_Number){
      if(param==null){
        setPhoneNumDisabled(false);
        console.log(phoneNumDisabled);
      
      }
      else{
      setPhoneNumDisabled(!phoneNumDisabled)}
    }
    if(param==formData.email){
      if(param==null){
      setEmailDisabled(false)}
      else{
        setEmailDisabled(!emailDisabled)

      }
    }
    if(param==formData.position){
      if(param==null){
        setPositionDisabled(false)
      }
      else{
      setPositionDisabled(!positionDisabled)
      }
    }
    if(param==formData.office_Phone_Num){
      if(param==null){
      setOfficeNumDisabled(false)
      }
      else{
        setOfficeNumDisabled(!officeNumDisabled)
      }
    }
    if(param==formData.dept_Area_Region_District){
      if(param==null){
        setDeptAreaDisabled(false);
      }
      else{
      setDeptAreaDisabled(!deptAreaDisabled)
      }
    }

  }
  const clickCancelHandler=param=>event=>{
    if(param==data.phone_Number){
      console.log(data.phone_Number)
      setFormData({
        ...formData,
        phone_Number:data.phone_Number
    })
    setPhoneNumDisabled(true)
  
  }
    if(param==data.position){
      
      setFormData({
        ...formData,
        position:data.position
    })
    setPositionDisabled(true)
  }
      


    if(param==data.dept_Area_Region_District){
      setFormData({
        ...formData,
        dept_Area_Region_District:data.dept_Area_Region_District
    })
    setDeptAreaDisabled(true)

  }
  if(param==data.office_Phone_Num){
    setFormData({
      ...formData,
      office_Phone_Num:data.office_Phone_Num
  })
  setOfficeNumDisabled(true)

}
if(param==data.email){
  setFormData({
    ...formData,
    email:data.email
})
setEmailDisabled(true)

}
  
    }

  



  const handleSubmit=(e)=>{
    e.preventDefault()
 
    const datas={
      staff_Id:formData.staff_Id,
      position:formData.position,
      email:formData.email,
      office_Phone_Num:formData.office_Phone_Num,
      phone_Number:formData.phone_Number,
      dept_Area_Region_District:formData.dept_Area_Region_District   
    };
    //console.log(data)
    //console.log(storeName);
    const token=JSON.parse(localStorage.getItem('token'))
    axios.post('http://localhost:8012/employeeupdate/add-employee',datas,{headers:{'Authorization':`Bearer ${token}`}})
    .then((res)=>{
      setRequestData(res.data)
      //showModal()
      hideModal()
      setSnackBarMessage('Request Generated')
      setSnackbarSeverity('Success')
      setSnackbar(true)

    }).catch((err)=>{
      setSnackBarMessage('error')
      setSnackbarSeverity('error')
      setSnackbar(true)
    })
}
/*const clickRequestHandler=param=>event=>{
  const token=JSON.parse(localStorage.getItem('token'))
  console.log(token)
  axios.put(`http://localhost:8012/employeeupdate/updateemployee/${param}`)
  .then((response)=>{
    console.log(response)
    hideModal()
    fetchData()
      setShowForm(false)
      console.log(showForm)
      setSnackBarMessage('Successfully Updated')
      setSnackbarSeverity('success')
      setSnackbar(true)
      window.location.reload(false)

  })
  .catch((err)=>{
      setSnackBarMessage('No changes Made')
      setSnackbarSeverity('error')
      setSnackbar(true)
  
  })

}*/
  /*const clickCancelRequestHandler=param=>event=>{
    event.preventDefault()
    const token=JSON.parse(localStorage.getItem('token'))
    console.log(token)
    axios.delete(`http://localhost:8012/employeeupdate/deleterequest/${param}`)
    .then((response)=>{
      hideModal()
      fetchData()
      console.log(showForm)
      setShowForm(false)
      console.log(showForm)
      setSnackBarMessage('No Chnages Made')
      setSnackbarSeverity('success')
      setSnackbar(true)
      setData([''])
      window.location.reload(false)
    })
    .catch((err)=>{
      console.log("error no data")
    
    });
  
  }*/

  

  const validate = (e) => {
    switch(e.target.name){
      case "staffId":
        dataForm.staff_Idvalid= e.target.value.length==5
        break
      default:
          break;

    }
    switch(e.target.name){
      case "staffId":
          if(!dataForm.staff_Idvalid){
            setError({
              ...dataForm,
              staff_Id: 'Staff Id should be of 5 character'
            })
            setShowForm(false)
          }
          else{
            setError({
              ...dataForm,
              staff_Id: ''
            })
      
          }
    }

  }

  
  return ( 
    <section>
      <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-xl-10">
        <div class="card rounded-3 text-black">
          <div class="row g-0">
              <div class="card-body p-md-5 mx-md-4">
                <h3 className='text-center'>Employee Update</h3>
                <br/>
                    <form>
                    {error.staff_Id && (
                        <div class="alert alert-danger">
                          <p>
                            {" "}
                            <strong> </strong> {error.staff_Id}
                          </p>
                        </div>
                      )}
                    <div class="form-outline mb-4 d-flex justify-content-center">
                            <TextField size='small'
                                label="staffId"
                                name="staffId"
                                type="text"
                                defaultValue={employeeName}
                                onChange={handleChange}
                                onBlur={validate}
                                variant="outlined" 
                                
                                />
                        </div>
                        <Button color='primary' className='form_custom-button' onClick={fetchData}>Fetch</Button>
                    </form>
                    <hr></hr>
                  {showForm==true&&<form onSubmit={handleSubmit}>
                  <div >  
                      <TextField className='ml-5'
                                size='small'
                                label="position"
                                name="position"
                                type="text"
                                disabled={positionDisabled}
                                value={formData.position}
                                onChange={handleFormChange}
                                variant="outlined"
                                required
                          
                                />
                      {positionDisabled==true ? <button color='primary' className='form_custom-button ml-2 btn btn-sm btn-primary mr-5' onClick={(e)=>clickHandler(formData.position)(e)}>Edit</button> 
                      :<><button color='primary' className='form_custom-button ml-1 btn btn-success btn-sm' onClick={(e)=>clickHandler(formData.position)(e)}>Save</button>
                      <button color='primary' className='form_custom-button btn btn-warning ml-1 btn-sm' onClick={(e)=>clickCancelHandler(data.position)(e)}>Cancel</button></>}
                      <TextField className='ml-5' size='small'
                                label="email"
                                name="email"
                                type="text"
                                disabled={emailDisabled}
                                value={formData.email}
                                onChange={handleFormChange}
                                variant="outlined"
                                required
                            
                                />  
                       {emailDisabled==true?<button color='primary' className='form_custom-button ml-2 btn btn-sm btn-primary ' onClick={(e)=>clickHandler(formData.email)(e)}>Edit</button>
                       :<><button color='primary' className='form_custom-button ml-1 btn btn-success btn-sm' onClick={(e)=>clickHandler(formData.email)(e)}>Save</button>
                       <button color='primary' className='form_custom-button btn btn-warning ml-1 btn-sm' onClick={(e)=>clickCancelHandler(data.email)(e)}>Cancel</button></>}
                       </div><br/>
                        

                       <div >  
                      <TextField className='ml-5' size='small'
                                label="office_Phone_Num"
                                name="office_Phone_Num"
                                type="text"
                                disabled={officeNumDisabled}
                                value={formData.office_Phone_Num}
                                onChange={handleFormChange}
                                variant="outlined"
                                required
                                
                                />     
                       {officeNumDisabled==true?<button color='primary' className='form_custom-button mr-5 ml-2 btn btn-sm btn-primary ' onClick={(e)=>clickHandler(formData.office_Phone_Num)(e)}>Edit</button>:
                       <><button color='primary' className='form_custom-button ml-1 btn btn-success btn-sm' onClick={(e)=>clickHandler(formData.office_Phone_Num)(e)}>Save</button>
                       <button color='primary' className='form_custom-button btn btn-warning ml-1 btn-sm' onClick={(e)=>clickCancelHandler(data.office_Phone_Num)(e)}>Cancel</button></>}

                       <TextField className='ml-5' size='small'
                                label="phone_Number"
                                name="phone_Number"
                                type="text"
                                disabled={phoneNumDisabled}
                                value={formData.phone_Number}
                                onChange={handleFormChange}
                                variant="outlined"
                                required
                                
                                />     
                       {phoneNumDisabled==true?<button color='primary' className='form_custom-button  ml-2 btn btn-sm btn-primary ' onClick={(e)=>clickHandler(formData.phone_Number)(e)}>Edit</button>:
                       <><button color='primary' className='form_custom-button  ml-1 btn btn-success btn-sm' onClick={(e)=>clickHandler(formData.phone_Number)(e)}>Save</button>
                       <button color='primary' className='form_custom-button btn btn-warning ml-1 btn-sm' onClick={(e)=>clickCancelHandler(data.phone_Number)(e)}>Cancel</button></>}
                      </div><br/>
                      
                      <div >  
                      <TextField className='ml-5' size='small'
                                label="dept_Area_Region_District"
                                name="dept_Area_Region_District"
                                type="text"
                                disabled={deptAreaDisabled}
                                value={formData.dept_Area_Region_District}
                                onChange={handleFormChange}
                                variant="outlined"
                                required
                             
                                />     
                         {deptAreaDisabled==true?<button color='primary' className='form_custom-button mr-5 ml-2 btn btn-sm btn-primary' onClick={(e)=>clickHandler(formData.dept_Area_Region_District)(e)}>Edit</button>:
                       <><button color='primary' className='form_custom-button ml-1 btn btn-success btn-sm' onClick={(e)=>clickHandler(formData.dept_Area_Region_District)(e)}>Save</button>
                       <button color='primary' className='form_custom-button btn btn-warning ml-1 btn-sm' onClick={(e)=>clickCancelHandler(data.dept_Area_Region_District)(e)}>Cancel</button></>}
                     
                       
                      
                      
                       </div><br/>
                       <button type='submit' color='primary' className='form_custom-button btn btn-success' disabled={!phoneNumDisabled || !positionDisabled ||!deptAreaDisabled ||!emailDisabled || !officeNumDisabled}>SUBMIT</button>
                    </form>}               
                  
        </div>
            </div>
           
          </div>
        </div>
      </div>
    </div>
        


      <Snackbar open={showSnackbar} autoHideDuration={6000} onClose={handleCloseSnackbar}>
            <Alert onClose={handleCloseSnackbar} severity={snackbarSeverity} sx={{width:'100%'}}>
                {snackbarMessage}
            </Alert>
        </Snackbar>
    </section>
  )
}

export default EmployeeUpdate
