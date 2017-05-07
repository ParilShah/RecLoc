//
//  RLNetworking.swift
//  RecLoc
//
//  Created by Paril Shah on 5/1/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import Foundation
import Alamofire
import SwiftyJSON

class RLNetworking{

    func fetchResponseUsingGet(url:String, parameters:[String:Any]?,block: @escaping (JSON)->Void){
        Alamofire.request(url).responseJSON { response in
                switch response.result {
                    case .success:
                        block(JSON(data: response.data!))
                    break
                    case .failure(let error):
                        print(error)
            }
//            if let json = response.result.value {
//                print("JSON: \(json)")
//                block(JSON(data: response.data!))
//            }
        }
    }
    
    func fetchResponseUsingPost(url:String, parameters:[String:Any]?,block: @escaping (JSON)->Void){
        Alamofire.request(url, method: .post, parameters: parameters, encoding: JSONEncoding.default, headers: nil).responseJSON {
                    response in
                    switch response.result {
                    case .success:
                        block(JSON(data: response.data!))
                        break
                    case .failure(let error):
                        print(error)
            }
        }
    }

    func uploadImageUsingPost(url:String, parameters:Any?,block: @escaping (JSON)->Void){
        let image = UIImage(named: "Mountain")
        Alamofire.upload(multipartFormData: { multipartFormData in
            if  let imageData = UIImageJPEGRepresentation(image!, 0.6) {
                multipartFormData.append(imageData, withName: "image", fileName: "Mountain.png", mimeType: "image/png")
                do {
                    let data = try JSONSerialization.data(withJSONObject:parameters as Any, options:[.prettyPrinted])
                    let dataString = String(data: data, encoding: String.Encoding.utf8)!
                    print(dataString)
                    // do other stuff on success
                    multipartFormData.append(dataString.data(using: String.Encoding.utf8)!, withName: "jsonRequest")                    
                } catch {
                    print("JSON serialization failed:  \(error)")
                }
            }
            
        }, usingThreshold: UInt64.init(),
           to: "http://localhost:8080/location/submitLocation",
           method: .post,
           headers: nil,
           encodingCompletion: { encodingResult in
            switch encodingResult {
            case .success(let upload, _, _):
                upload.responseJSON { response in
                    debugPrint(response)
                }
            case .failure(let encodingError):
                print(encodingError)
            }
        })
        
       }

}
