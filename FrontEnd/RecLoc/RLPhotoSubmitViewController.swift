//
//  RLPhotoSubmitViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 4/5/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import TagListView

class RLPhotoSubmitViewController: UIViewController, TagListViewDelegate{
    
    public var placeImage:UIImage!
    var uploadButton: UIBarButtonItem!
    @IBOutlet weak var tagListView:TagListView!
    @IBOutlet weak var placeImageView:UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        tagListView.delegate = self
        tagListView.alignment = .left // possible values are .Left, .Center, and .Right
        tagListView.addTag("Forest")
        tagListView.insertTag("Mountain", atIndex: 1)
        uploadButton = UIBarButtonItem.init(title: "Upload", style: .plain, target: self, action: #selector(pressUpload(Sender:)))
        self.navigationItem.rightBarButtonItem = uploadButton
        self.placeImageView!.image = placeImage
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - TagListViewDelegate
    func tagPressed(_ title: String, tagView: TagView, sender: TagListView) {
        print("Tag pressed: \(title), \(sender)")
    }
    
    func pressUpload(Sender: Any?) {
        print("Upload")
    }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
