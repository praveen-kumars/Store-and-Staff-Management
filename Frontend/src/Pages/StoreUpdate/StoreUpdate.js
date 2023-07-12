import {React,useState,useEffect} from 'react'
import axios from 'axios'
import './StoreUpdate.css'
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
import { set } from 'lodash'


function StoreUpdate() {

  const [isOpen, setIsOpen] = useState(false);

  const [valueError,setValueError]=useState();

  const showModal = () => {
    setIsOpen(true);
  };

  
  const [dataForm, setDataForm] = useState({
    addressValid: false,
    areaCodeValid:false,
    PhoneValid: false, 
    storeNameValid:false,
  });

  
  const [error, setError] = useState({ address:undefined,phone_Number:undefined,area_Region_Code:undefined,storeName:undefined});
  const[submitStatus,setSubmitStatus]=useState(false)

  const hideModal = () => {
    setIsOpen(false);
    setSnackBarMessage('Request Generated')
      setSnackbarSeverity('success')
      setSnackbar(true)
      console.log(data)
      setShowForm(false)
      setFormData({
        ...formData,
        store_Id:'',
        address:'',
        phone_Number:'',
        area_Region_Code:'',
        mon_Hours:'',
        tue_Hours:'',
        wed_Hours:'',
        thru_Hours:'',
        fri_Hours:'',
        sat_Hours:'',
        sun_Hours:''
    })
  };
  const [data,setData]=useState({})
  const [requestData,setRequestData]=useState({})

  const [formData,setFormData]=useState({})

  const [storeName,setStoreName]=useState()
  const [showForm,setShowForm]=useState()
  const [disabled,setDisabled]=useState(true)
  const [phoneDisabled,setPhoneDisabled]=useState(true)
  const [addressDisabled,setAddressDisabled]=useState(true)
  const [areaCodeDisabled,setAreaCodeDisabled]=useState(true)
  //const [showModal,setShowModal]=useState(false);

  const [snackbarMessage,setSnackBarMessage]=useState()
    const [snackbarSeverity,setSnackbarSeverity]=useState()
    const [showSnackbar,setSnackbar]=useState()

    const handleCloseSnackbar=(event,reason)=>{
            setSnackBarMessage('')
            setSnackbarSeverity('')
            setSnackbar(false)
    };


  const fetchData=()=>{
    //console.log(storeName);
    if(!dataForm.storeNameValid){
      return
    }
    const token=JSON.parse(localStorage.getItem('token'))
    axios.get(`http://localhost:8011/storeupdate/retrieve/${storeName}`,{headers:{'Authorization':`Bearer ${token}`}})
    .then((response)=>{
      setData(response.data)
      setFormData(response.data)
      setShowForm(true)
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
    setStoreName(e.target.value)
    //dataForm.storeNameValid= e.target.value.length==5
    /*if(!dataForm.storeNameValid){
      setError({
        ...dataForm,
        storeName: 'StoreId should be of 5 character'
      })
      setShowForm(false)
    }
    else{
      setError({
        ...dataForm,
        storeName: ''
      })

    }*/

    
  }

  const handleFormChange=(event)=>{
    event.preventDefault()
   
    setFormData({
        ...formData,
        [event.target.name]:event.target.value!==""?event.target.value : null
    })
}

  const handleEditClick=(event)=>{
    setDisabled(!disabled)
  }

  const clickHandler=param=>event=>{
    if(param==formData.phone_Number){
      if(param==null){
        setPhoneDisabled(false);
        setValueError=event.target.name

      }
      else{
      setPhoneDisabled(!phoneDisabled)
    
    }
    }
    if(param==formData.address){
      if(param==null){
        setAddressDisabled(false);
        
      }
      else{
        setAddressDisabled(!addressDisabled)
        
    }
    }
    if(param==formData.area_Region_Code){
      if(param==null){
        areaCodeDisabled(false);
      }
      else{
        setAreaCodeDisabled(!areaCodeDisabled)
      }
    }

  }

  const clickCancelHandler=param=>event=>{
    if(param==data.phone_Number){
      
      setFormData({
        ...formData,
        phone_Number:data.phone_Number
    })
    setPhoneDisabled(true)
  
  }
    if(param==data.address){
      //console.log(data.address);
     // console.log(formData.address)
     // console.log(formData);
      setFormData({
        ...formData,
        address:data.address
    })
    setAddressDisabled(true)
  }
      


    if(param==data.area_Region_Code){
      setFormData({
        ...formData,
        area_Region_Code:data.area_Region_Code
    })
    setAreaCodeDisabled(true)

  }
  
    }

  const handleTimeClick=()=>{
    setFormData({
      ...formData,
      mon_Hours:data.mon_Hours,
      tue_Hours:data.tue_Hours,
      wed_Hours:data.wed_Hours,
      thru_Hours:data.thru_Hours,
      fri_Hours:data.fri_Hours,
      sat_Hours:data.sat_Hours,
      sun_Hours:data.sun_Hours
  })
    setDisabled(true)

  }

  



  const handleSubmit=(e)=>{
      e.preventDefault()
 
      const datas={
      store_Id:formData.store_Id,
      address:formData.address,
      phone_Number:formData.phone_Number,
      area_Region_Code:formData.area_Region_Code,
      mon_Hours:formData.mon_Hours,
      tue_Hours:formData.tue_Hours,
      wed_Hours:formData.wed_Hours,
      thru_Hours:formData.thrue_Hours,
      fri_Hours:formData.fri_Hours,
      sat_Hours:formData.sat_Hours,
      sun_Hours:formData.sun_Hours,   
    };
    //console.log(data)
    //console.log(storeName);
    const token=JSON.parse(localStorage.getItem('token'))
    axios.post('http://localhost:8011/storeupdate/add-store',datas,{headers:{'Authorization':`Bearer ${token}`}})
    .then((res)=>{
      setRequestData(res.data)
      //showModal()
      setSnackBarMessage('Request Generated')
      setSnackbarSeverity('success')
      setSnackbar(true)
      hideModal()
      

    }).catch((err)=>{
      

    })

}
/*const clickRequestHandler=param=>event=>{
  const token=JSON.parse(localStorage.getItem('token'))
  console.log(token)
  axios.put(`http://localhost:8011/storeupdate/updatestore/${param}`)
  .then((response)=>{
      hideModal()
      setSnackBarMessage('Successfully Updated')
      setSnackbarSeverity('success')
      setSnackbar(true)

  })
  .catch((err)=>{
      setSnackBarMessage('No changes Made')
      setSnackbarSeverity('error')
      setSnackbar(true)
  
  })

}*/
/*  const clickCancelRequestHandler=param=>event=>{
    const token=JSON.parse(localStorage.getItem('token'))
    axios.delete(`http://localhost:8011/storeupdate/deleterequest/${param}`)
    .then((response)=>{
      hideModal()
      setSnackBarMessage('No Chnages Made')
      setSnackbarSeverity('success')
      setSnackbar(true)
    })
    .catch((err)=>{
      console.log("error no data")
    
    });
  
  }*/

  const validate = (e) => {
  
    switch(e.target.name){
      
      case "phone_Number":
        
        dataForm.PhoneValid =e.target.value.length>1;
        console.log(e.target.value.length)
        console.log(dataForm.PhoneValid)
        break;
      case "address":
        dataForm.addressValid=e.target.value.length>1;
        break;
      case "area_Region_Code":
        dataForm.areaCodeValid=e.target.value.length>1;
        break;
      case "storeName":
        dataForm.storeNameValid= e.target.value.length==5
        break

        default:
          break;
    }

    switch(e.target.name){
      case "area_Region_Code":
        if(!dataForm.areaCodeValid){
          setError({
            ...dataForm,
            area_Region_Code:"Area Region Code cannot be empty"
          })
          console.log(error.area_Region_Code)
        }
        else{
          setError({
            ...dataForm,
            area_Region_Code: ''
          })

        }
        break
      case "address":
        if(!dataForm.addressValid){
          setError({
            ...dataForm,
            address: 'Address cannot be empty'
          })
        }
        else{
          setError({
            ...dataForm,
            address: ''
          })

        }
        break
        case "phone_Number":
          if(!dataForm.PhoneValid){
            setError({
              ...dataForm,
              phone_Number: 'Phone Number cannot be empty'
            })
            console.log(error.phone_Number)
          }
          else{
            setError({
              ...dataForm,
              phone_Number: ''
            })
  
          }
          break
        case "storeName":
          if(!dataForm.storeNameValid){
            setError({
              ...dataForm,
              storeName: 'StoreId should be of 5 character'
            })
            setShowForm(false)
          }
          else{
            setError({
              ...dataForm,
              storeName: ''
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
                <h3 className='text-center'>Store Update</h3>
                <br/>
     
                    <form >
                    {error.storeName && (
                        <div class="alert alert-danger">
                          <p>
                            {" "}
                            <strong> </strong> {error.storeName}
                          </p>
                        </div>
                      )}
                    <div class="form-outline mb-4 d-flex justify-content-center">
                            <TextField size='small'
                                label="store Name"
                                name="storeName"
                                type="text"
                                defaultValue={storeName}
                                onChange={handleChange}
                                variant="outlined"
                                onBlur={validate}
                                required
                        
                                />
                        </div>
                        <Button color='primary' className='btn btn-primary d-flex justify-content-center p-1' onClick={fetchData}>Fetch</Button>
                    </form>
                    <hr></hr>
                  {showForm==true&&<form onSubmit={handleSubmit}>
                   
                    <div >  
                 
                      <textarea rows="3" cols="50" className='ml-5'
                                size='small'
                                label="address"
                                name="address"
                                type="text"
                                disabled={addressDisabled}
                                value={formData.address}
                                onChange={handleFormChange}
                                variant="outlined"
                                onBlur={validate}
                                required>
                                </textarea>
                      {addressDisabled==true ? <button  color='primary' className='form_custom-button btn btn-primary btn-sm  mr-5 ml-4' onClick={(e)=>clickHandler(formData.address)(e)}>Edit</button> 
                      :<><button color='primary' className='form_custom-button btn btn-success btn-sm  ml-1' onClick={(e)=>clickHandler(formData.address)(e)}>Save</button>
                      <button color='primary' className='form_custom-button btn btn-warning btn-sm ml-1' onClick={(e)=>clickCancelHandler(data.address)(e)}>Cancel</button></>}
                      
                      
                      <TextField className='ml-5' size='small'
                                label="phone_Number"
                                name="phone_Number"
                                type="text"
                                disabled={phoneDisabled}
                                value={formData.phone_Number}
                                onChange={handleFormChange}
                                variant="outlined"
                                onBlur={validate}
                                required
                                />  
                       {phoneDisabled==true?<button color='primary' className=' btn btn-primary btn-sm  ml-1' onClick={(e)=>clickHandler(formData.phone_Number)(e)}>Edit</button>
                       :<><button color='primary' className='form_custom-button btn btn-success btn-sm ml-1' onClick={(e)=>clickHandler(formData.phone_Number)(e)}>Save</button>
                       <button color='primary' className='form_custom-button btn btn-warning btn-sm ml-1' onClick={(e)=>clickCancelHandler(data.phone_Number)(e)}>Cancel</button></>}
                 
                       </div><br/>
                       <div >  
                      <TextField className='ml-5' size='small'
                                label="area_Region_Code"
                                name="area_Region_Code"
                                type="text"
                                disabled={areaCodeDisabled}
                                value={formData.area_Region_Code}
                                onChange={handleFormChange}
                                variant="outlined"
                                onBlur={validate}
                                required
                                />     
                       {areaCodeDisabled==true?<button color='primary' className='btn btn-primary btn-sm  mr-5 ml-1' onClick={(e)=>clickHandler(formData.area_Region_Code)(e)}>Edit</button>:
                       <><button color='primary' className='btn btn-success btn-sm ml-1' onClick={(e)=>clickHandler(formData.area_Region_Code)(e)}>Save</button>
                       <button color='primary' className='btn btn-warning btn-sm ml-1' onClick={(e)=>clickCancelHandler(data.area_Region_Code)(e)}>Cancel</button></>}

                       <TextField className='ml-5' size='small'
                                label="mon_Hours"
                                name="mon_Hours"
                                type="text"
                                disabled={disabled}
                                value={formData.mon_Hours}
                                onChange={handleFormChange}
                                variant="outlined"
                                />     
                    
                       </div><br/>
                       <div >  
                      <TextField className='mx-5' size='small'
                                label="tue_Hours"
                                name="tue_Hours"
                                type="text"
                                disabled={disabled}
                                value={formData.tue_Hours}
                                onChange={handleFormChange}
                                variant="outlined"
                              
                                />     
                       
                       <TextField className='mx-5' size='small'
                                label="wed_Hours"
                                name="wed_Hours"
                                type="text"
                                disabled={disabled}
                                value={formData.wed_Hours}
                                onChange={handleFormChange}
                                variant="outlined"
                           
                                />   
                                </div><br/>
                                <div >  
                      <TextField className='mx-5' size='small'
                                label="thur_Hours"
                                name="thru_Hours"
                                type="text"
                                disabled={disabled}
                                value={formData.thru_Hours}
                                onChange={handleFormChange}
                                variant="outlined"
                             
                             
                                /> 
                        <TextField className='mx-5' size='small'
                                label="fri_Hours"
                                name="fri_Hours"
                                type="text"
                                disabled={disabled}
                                value={formData.fri_Hours}
                                onChange={handleFormChange}
                                variant="outlined"
                                
                               
                                />  
                                </div><br/>
                                <div >  
                      <TextField className='mx-5' size='small'
                                label="sat_Hours"
                                name="sat_Hours"
                                type="text"
                                disabled={disabled}
                                value={formData.sat_Hours}
                                onChange={handleFormChange}
                                variant="outlined"
                              
                                />  
                        <TextField className='ml-5' size='small'
                                label="sun_Hours"
                                name="sun_Hours"
                                type="text"
                                disabled={disabled}
                                value={formData.sun_Hours}
                                onChange={handleFormChange}
                                variant="outlined"
                            
                                />   
                                {disabled==true?<button color='primary' className='form_custom-button ml-1 btn btn-primary btn-sm' onClick={handleEditClick}>Edit</button>:
                       <><button color='primary' className='form_custom-button ml-1 btn btn-success btn-sm' onClick={handleEditClick}>Save</button>
                       <button color='primary' className='form_custom-button ml-1 btn btn-warning btn-sm' onClick={handleTimeClick}>Cancel</button></>} 
                                </div><br/>
                       <button type='submit' disabled={phoneDisabled==false || addressDisabled==false || areaCodeDisabled==false || disabled==false} className='btn btn-success '>SUBMIT</button>
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

export default StoreUpdate