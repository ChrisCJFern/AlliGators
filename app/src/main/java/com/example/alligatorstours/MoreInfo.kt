package com.example.alligatorstours

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MoreInfo : AppCompatActivity() {

    private var locDrawables = HashMap<String, Int>()
    private var locInfo = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)

        locDrawables["Reitz Union"] = R.drawable.reitzunionicon
        locDrawables["Plaza of the Americas"] = R.drawable.plazaoftheamericas
        locDrawables["Century Tower"] = R.drawable.centurytower
        locDrawables["Career Connections Center"] = R.drawable.careerconnectionscenter
        locDrawables["College of Journalism"] = R.drawable.collegeofjournalism
        locDrawables["Marston Science Library"] = R.drawable.marstonsciencelibrary
        locDrawables["Ben Hill Griffin Stadium"] = R.drawable.benhillgriffinstadium
        locDrawables["The Hub"] = R.drawable.thehub
        locDrawables["The Potato (Turlington Rock)"] = R.drawable.thepotato
        locDrawables["The French Fries"] = R.drawable.thefrenchfries
        locDrawables["Tigert Hall"] = R.drawable.tigerthall
        locDrawables["Field and Fork"] = R.drawable.fieldandfork
        locDrawables["Criser Hall"] = R.drawable.criserhall
        locDrawables["Broward Hall"] = R.drawable.browardhall
        locDrawables["Stephen C. O'Connell Center"] = R.drawable.stephenoconnellcenter
        locDrawables["University Auditorium"] = R.drawable.universityauditorium
        locDrawables["Infirmary"] = R.drawable.infirmary
        locDrawables["Peabody Hall"] = R.drawable.peabodyhall
        locDrawables["Newell Hall"] = R.drawable.newellhall
        locDrawables["Murphree Hall"] = R.drawable.murphreehall
        locDrawables["Smathers Library"] = R.drawable.smatherslibrary

        locInfo["Reitz Union"] = "This building, officially called the J. Wayne Reitz Student Union," +
                " was named for the University’s 5th president. The Reitz Union was completed in 1967," +
                " and completely remodeled in 2016.  The Reitz is a student resource, and offers many" +
                " services and opportunities for all UF students."
        locInfo["Plaza of the Americas"] = "The Plaza of the Americas is one of the two “green areas”" +
                " on campus (along with the Reitz Union North Lawn), which means that no buildings may" +
                " ever be constructed over the portion that remains.  It got its name in 1931, when" +
                " students planted 21 trees in the plaza to honor the 21 countries that attended the" +
                " first meeting of the International Latin American Association.  The plaza has played" +
                " host to a “Karma-Free” lunch served by the Hare Krishna since 1977."
        locInfo["Century Tower"] = "Started in 1953 and completed in 1956, the tower was built to " +
                "commemorate the University’s centennial and to serve as a memorial for UF students " +
                "and faculty who fought in World Wars I and II. In the 1970s the 61-bell carillon was " +
                "installed, and it now chimes every fifteen minutes and tolls every hour from " +
                "8am to 8pm. During fall and spring, at 12:35 and 4:55 each weekday, a carillonneur " +
                "climbs the 11.5 flights of stairs and plays a concert of the bells with music " +
                "ranging from classical to contemporary."
        locInfo["Career Connections Center"] = ""
        locInfo["College of Journalism"] = ""
        locInfo["Marston Science Library"] = "One of nine libraries on campus, the Marston Science " +
                "Library serves more than 1.5 million visitors a year, and specializes in physical, " +
                "life and earth sciences, engineering, agriculture and mathematics. The library is " +
                "set up on an “elevated volume control system” meaning the higher the floor, the " +
                "quieter it is. The Starbucks is open all hours the library is open. The first floor " +
                "was recently completely renovated in 2014 and includes a new Collaboration Commons " +
                "created in response to the students’ desire for more space to meet and study.  " +
                "The 26,000 square foot Commons has seating for more than 700 and includes 21 group " +
                "study rooms with large high-definition monitors, a data visualization room and " +
                "MADE@UF, the first lab in an academic library fully devoted to mobile application " +
                "development.  The library has computers on every floor as well as printers, " +
                "scanners, and even a room full of 3-D printers."
        locInfo["Ben Hill Griffin Stadium"] = "Also known as “The Swamp,” Ben Hill Griffin Stadium " +
                "seats 88,548 people.  This makes it the 13th largest stadium in the nation and " +
                "the largest in the state.  In 2014, USA Today ranked Ben Hill Griffin Stadium as " +
                "the 6th best college venue in the United States.\nIt has also been considered one " +
                "of the most difficult places to play due to the design of the stadium; the field is " +
                "about 30 feet below ground level and fans are sometimes only 10 feet away from players."
        locInfo["The Hub"] = "The Hub is home to additional dining options, as well as the UF " +
                "Computing Help Desk.  They offer tech support and subsidized prices for most services " +
                "needed. The Computing Help Desk does not fix hardware issues, but will handle any " +
                "technical problem to the best of their abilities."
        locInfo["The Potato (Turlington Rock)"] = "This sculpture, called “The Potato,” is an " +
                "approximately 30-million-year-old chert rock donated to the UF Geology Department " +
                "by the Crushed Rock Company.  It was placed in Turlington as part of a beautification " +
                "project. Students often dress it up at Halloween."
        locInfo["The French Fries"] = "Installed in 1988, the sculpture is officially entitled “Alachua,” " +
                "but is colloquially referred to by students as “the french fries.”  It was designed " +
                "to symbolize the crown of the Alachua Native American. The Administration at the time " +
                "disliked it so much they attempted – unsuccessfully – to have it moved to the basement.  "
        locInfo["Tigert Hall"] = "Completed in 1950, Tigert Hall is home to the President’s Office as " +
                "well as the Office of Student Affairs. Also located in Tigert Hall is the University " +
                "Ombudsman, whose purpose is to assist students, staff, and faculty in resolving problems " +
                "and conflicts that arise in the course of interacting with the University of Florida. " +
                "Tigert Hall was the first building on campus to include a computer room and one of " +
                "the first to feature air conditioning. "
        locInfo["Field and Fork"] = "Field and Fork Pantry was opened to assist members of our campus " +
                "community who are experiencing food insecurity. Any individual with a Gator1 ID can " +
                "attend, and will be given a basket to fill up with food to last a week completely " +
                "FREE. Items that are available are non-perishable food items, toiletries, and occasional " +
                "fresh produce from the UF Community Garden and UF farms. "
        locInfo["Criser Hall"] = "Criser Hall houses the Office of Admissions, the Office of Financial " +
                "Affairs, and the Registrar’s Office. Additionally, the Student Employment Office is " +
                "located on the ground floor of Criser Hall. Students interested in a work-study job " +
                "on campus must acquire a job permit from this office. Finally, the University Bursar " +
                "is also located in Criser. This is where administrators collect students' tuition " +
                "and fee charges, and disburse financial awards, such as loans, grants, and scholarships."
        locInfo["Broward Hall"] = "There is a free tutoring center in Broward Hall. It offers free help " +
                "in humanities, language, math, science, etc. There are also specialized tutoring " +
                "centers for subjects such as Chemistry, Statistics, and more."
        locInfo["Stephen C. O'Connell Center"] = "The Stephen C. O’Connell Center, also called the " +
                "O’Dome, is named after UF’s sixth President. The recently renovated arena seats " +
                "10,133 fans and is home to the men’s and women’s basketball, volleyball, indoor track, " +
                "gymnastics, swimming and diving teams. \n" +
                "The Center includes the new Exactech Arena, a private court, two weight rooms, an " +
                "indoor swimming pool, an indoor track, a private gymnastics area, a dance studio, " +
                "and a martial arts area.\n"
        locInfo["University Auditorium"] =  "The University Auditorium was finished in the mid 1920’s, " +
                "then renovated and expanded as a bicentennial project in 1976, which led to its unique " +
                "dual architecture. The University Auditorium is one of the only buildings on campus that " +
                "is included in the National Register of Historic Places. It seats 867 people and is" +
                " used for Fine Arts events, concerts and as a venue for invited speakers. It is also " +
                "home to the Anderson Memorial Pipe Organ, one of the largest pipe organs in the " +
                "United States."
        locInfo["Infirmary"] = "The UF Student Health Care Center (SHCC) offers a wide variety of " +
                "services to help keep students at their best, including general medical care, a " +
                "women's health clinic, sports medicine services, nutrition counseling, a full-service " +
                "pharmacy, X-ray and lab services. Students are assigned to a specific team of doctors " +
                "and nurses when they arrive that they will stay with during their time at UF. " +
                "This builds rapport and helps protect personal information.\n" +
                "UF Health at Shands Hospital is located on the south side of the UF campus, and is " +
                "the state’s leading health care referral system and one of the southeast’s most " +
                "respected health care providers.\n"
        locInfo["Peabody Hall"] = "Peabody Hall is also home to the Dean of Students Office, which " +
                "includes “all things students,” ranging from student conduct and conflict resolution, " +
                "to new student and family programs, and U Matter We Care. U Matter, We Care is a big " +
                "part of the culture of care on UF’s campus: the care team reaches out to people in " +
                "distress and helps them prioritize issues and come up with a success plan and then " +
                "follow up until no longer needed.\n" +
                "Through the Dean of Students Office, information is available on a wide variety of " +
                "programs, including: Preview, the UF orientation for new students and their families; " +
                "First Year Florida, a one credit hour transition course; Common Reading Program; \n" +
                "Gator Career Closet; Collegiate Veterans Success Center and the Disability Resource " +
                "Center. \n"
        locInfo["Newell Hall"] = "Opened in 2017, Newell Hall is a 24/7 learning commons, made by UF " +
                "students for UF students.  Located in UF's third oldest building, the inside is high " +
                "tech, comfortable, and designed for collaborative learning and study.  An Au Bon " +
                "Pain provides refreshments for hungry students while they learn and engage.  " +
                "Newell is run by the Dean of Students Office, in partnership with Student Government. "
        locInfo["Murphree Hall"] = "The Murphree Commons Courtyard is located in the historic district " +
                "of campus and offers a wide array of housing styles including single, double, and " +
                "triple rooms. Freshmen are not required to live on campus, but 82% of students living " +
                "in residence halls are first year students. UF estimates that approximately 23% of " +
                "all students live on campus.\nFor more information about on- and off-campus housing " +
                "options, please visit the website at https://housing.ufl.edu. \n"
        locInfo["Smathers Library"] = "The UF Libraries are the largest information resource system " +
                "in the state of Florida, with seven locations containing close to 5 million print " +
                "volumes. The libraries have over 3 million visitors a year – that’s more visitors " +
                "than the O’Connell Center and Ben Hill Griffin Stadium combined! – plus many more " +
                "online visitors. The UF Digital Collection houses more than 13 million digitized " +
                "pages for use by students and researchers worldwide.\n" +
                "Library West provides hundreds of places for individual study, 200+ computers, numerous " +
                "group study rooms, a Starbucks coffee shop, and iPads for checkout. The library is " +
                "open 24/7 during the fall and spring semesters.\n"

        val title = intent.extras!!.getString("TITLE")

        val headerTitle = findViewById<TextView>(R.id.locationName)
        val locIcon = findViewById<ImageView>(R.id.locationIcon)
        val locationInfo = findViewById<TextView>(R.id.information)

        headerTitle.text = title
        locationInfo.text = locInfo[title]
        locDrawables.get(title)?.let { locIcon.setImageResource(it) }
    }
}